package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Player;
import tile.Tile;

public class GPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int screenWidth;
	public static int screenHeight;
	
	public static int FPS = 60;
	
	public static int mouseStartX;
	public static int mouseStartY;
	public static int mouseX;
	public static int mouseY;
	public static int mouseButton;
	public static boolean mouseDown;
	public static boolean lastMouseDown;
	
	public static boolean paused = false;
	
	public static int cameraX = 0;
	public static int cameraY = 0;
	
	public static int currentBlock = 1;

	public static MouseHandler mouseH = new MouseHandler();
	public static MouseMotionHandler mouseMotionH = new MouseMotionHandler();
	public static MouseWheelHandler mouseWheelH = new MouseWheelHandler();
	public static KeyHandler keyH = new KeyHandler();
	
	Thread gThread;
	JFrame frame;
	
	Player player = new Player();
	
	public GPanel(JFrame frame) {
		this.frame = frame;
		
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		
		this.addMouseListener(mouseH);
		this.addMouseMotionListener(mouseMotionH);
		this.addMouseWheelListener(mouseWheelH);
		this.addKeyListener(keyH);
		
		Tile.init();
		player.init();
	}
	
	public void startGThread() {
		
		gThread = new Thread(this);
		gThread.start();
	}
	
	@Override @SuppressWarnings("unused")
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while (gThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime); 
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		player.update();
		
		if (keyH.blockLeftKeyPressed) {
			currentBlock--;
			keyH.blockLeftKeyPressed = false;
		}
		if (keyH.blockRightKeyPressed) {
			currentBlock++;
			keyH.blockRightKeyPressed = false;
		}
		
		if (currentBlock > Tile.tileSprites.length) {
			currentBlock = 1;
		} else if (currentBlock < 1) {
			currentBlock = Tile.tileSprites.length;
		}
		
		cameraX = Main.round(player.x * Tile.tileMultiplier - screenWidth/2 - player.spriteWidth/2);
		cameraY = Main.round(player.y * Tile.tileMultiplier - screenHeight/2 - player.spriteHeight/2);
		
		if (mouseDown) {
			int placeX = (mouseX + cameraX)/Tile.tileMultiplier/Tile.tileSize;
			int placeY = (mouseY + cameraY)/Tile.tileMultiplier/Tile.tileSize;
			if (placeY < Tile.tiles.length && placeY >= 0 && placeX < Tile.tiles[0].length && placeX >= 0) {
				if (mouseButton == 1) {
					Tile.tiles[(mouseY + cameraY)/Tile.tileMultiplier/Tile.tileSize][(mouseX + cameraX)/Tile.tileMultiplier/Tile.tileSize] = new Tile(currentBlock);
				} else {
					Tile.tiles[(mouseY + cameraY)/Tile.tileMultiplier/Tile.tileSize][(mouseX + cameraX)/Tile.tileMultiplier/Tile.tileSize] = null;
				}
			}
		}
		
		if (keyH.saveKeyPressed) {
			try {
				File mapFile = new File("res/map1.txt");
				mapFile.delete();
				FileWriter mapWriter = new FileWriter("res/map1.txt");
				for (int ty = 0; ty < Tile.tiles.length; ty++) {
					for (int tx = 0; tx < Tile.tiles[0].length; tx++) {
						if (Tile.tiles[ty][tx] != null) {
							mapWriter.append(String.valueOf(Tile.tiles[ty][tx].tileIndex + 1) + " ");
						} else {
							mapWriter.append("0 ");
						}
					}
					mapWriter.append("\n");
				}
				
				mapWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			keyH.saveKeyPressed = false;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		  //  RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransform origTransform = g2.getTransform();
		
		g2.setColor(new Color(50,100,220));
		g2.fillRect(0, 0, screenWidth + 200, screenHeight + 200);
		
	    g2.setStroke(new BasicStroke(Tile.tileMultiplier));
	    
	    g2.translate(-cameraX, -cameraY);
		
		Tile.draw(g2);
		player.draw(g2);
		
		g2.setTransform(origTransform);

	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, Tile.tileMultiplier * Tile.tileSize + 6, Tile.tileMultiplier * Tile.tileSize + 6);
	    g2.drawImage(Tile.tileSprites[currentBlock-1], 3 , 3, Tile.tileMultiplier * Tile.tileSize, Tile.tileMultiplier * Tile.tileSize, null);
	    
		g2.dispose();
	}
}

