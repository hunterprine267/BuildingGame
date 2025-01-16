package main;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	public static GPanel gp;
	
	public static Random rand = new Random();
	
	public static void main(String[] args) {
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Building 4 fun");
		window.setSize(1200, 800);
        window.setMinimumSize(new Dimension(600, 625));
        
		gp = new GPanel(window);
		
		window.add(gp);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
		
		window.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				GPanel.screenWidth = (int) window.getContentPane().getSize().getWidth();
				GPanel.screenHeight = (int) window.getContentPane().getSize().getHeight();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		gp.startGThread();
	}
	
	public static BufferedImage getImage(String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("res/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static int round(double num) {
		return (int) Math.round(num);
	}
	
	public static double distance(double[] p1, double[] p2) {
		double distX = (p1[0]-p2[0]);
		double distY = (p1[1]-p2[1]);
		double distZ = (p1[2]-p2[2]);
		
		return Math.sqrt(distX * distX + 
						 distY * distY + 
						 distZ * distZ);
	}
	
	public static boolean overlaps(Rectangle rect1, Rectangle rect2) {
		
		if (rect1.x >= rect2.x + rect2.width || rect2.x >= rect1.x + rect1.width) {
			return false;
		}
		
		if (rect1.y >= rect2.y + rect2.height || rect2.y >= rect1.y + rect1.height) {
			return false;
		}
		
		return true;
	}
}
