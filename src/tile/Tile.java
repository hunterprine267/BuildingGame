package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import main.Main;

public class Tile {
	
	public static Tile[][] tiles;
	
	public static BufferedImage[] tileSprites = new BufferedImage[] {
			Main.getImage("grass.png"),
			Main.getImage("dirt.png"),
			Main.getImage("sand.png"),
			Main.getImage("stone.png"),
			Main.getImage("mystery.png"),
			Main.getImage("stone_bricks.png"),
			Main.getImage("wood.png"),
			Main.getImage("plank.png")
	};
	
	public static void init() {
	
		File file = new File("res/map1.txt");
			
		try (Scanner fileScanner = new Scanner(file)) {
			int yCount = 0;
			int xCount = 0;
			
			while (fileScanner.hasNextLine()) {
				yCount++;
				if (fileScanner.hasNextLine()) {
					xCount = fileScanner.nextLine().length()/2;
				} else {
					break;
				}
			}
			tiles = new Tile[yCount][xCount];
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (Scanner fileScanner = new Scanner(file)) {
			for (int ty = 0; ty < Tile.tiles.length; ty++) {
				for (int tx = 0; tx < Tile.tiles[0].length; tx++) {
					int index = fileScanner.nextInt();
					if (index != 0) {
						tiles[ty][tx] = new Tile(index);
					} else {
						tiles[ty][tx] = null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int tileMultiplier = 2;
	public static int tileSize = 8;
	
	public int tileIndex;
	
	public Tile(int index) {
		tileIndex = index - 1;
	}
	
	public static void draw(Graphics2D g2) {
		
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (tiles[y][x] != null) {
					g2.drawImage(tileSprites[tiles[y][x].tileIndex], x * tileMultiplier * tileSize, y * tileMultiplier * tileSize, tileMultiplier * tileSize, tileMultiplier * tileSize, null);
				}
			}
		}
		
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (tiles[y][x] != null) {
					
					g2.setColor(new Color(20,20,20));
					
					if (y > 0 && tiles[y-1][x] == null) {
						g2.drawLine(x * tileMultiplier * tileSize, y * tileMultiplier * tileSize, (x + 1) * tileMultiplier * tileSize, y * tileMultiplier * tileSize);
					}
					
					if (y < tiles.length-1 && tiles[y+1][x] == null) {
						g2.drawLine(x * tileMultiplier * tileSize, (y + 1) * tileMultiplier * tileSize, (x + 1) * tileMultiplier * tileSize, (y + 1) * tileMultiplier * tileSize);
					}
					
					if (x > 0 && tiles[y][x-1] == null) {
						g2.drawLine(x * tileMultiplier * tileSize, y * tileMultiplier * tileSize, x * tileMultiplier * tileSize, (y + 1) * tileMultiplier * tileSize);
					}
					
					if (x < tiles[0].length-1 && tiles[y][x+1] == null) {
						g2.drawLine((x + 1) * tileMultiplier * tileSize, y * tileMultiplier * tileSize, (x + 1) * tileMultiplier * tileSize, (y + 1) * tileMultiplier * tileSize);
					}
				}
			}
		}
	}
}
