package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GPanel;
import main.Main;
import tile.Tile;

public class Player extends Entity {
	
	int width = 8;
	int height = 8;
	
	boolean onGround = false;
	
	ArrayList<Tile> tileHits = new ArrayList<Tile>();

	public void init() {
		x = Tile.tiles[0].length/2 * Tile.tileSize;
		y = 16 * Tile.tileSize;
		xAcc = .25;
		yAcc = .25;
		
		rect = new Rectangle(Main.round(x * Tile.tileMultiplier), Main.round(y * Tile.tileMultiplier), width * Tile.tileMultiplier, height * Tile.tileMultiplier);
		
		sprites = new BufferedImage[] {
				Main.getImage("player1.png")
		};
	}
	
	public void update() {
		
		if (GPanel.keyH.leftKeyPressed) {
			xVel -= xAcc;
		}
		if (GPanel.keyH.rightKeyPressed) {
			xVel += xAcc;
		}
		if (GPanel.keyH.jumpKeyPressed && onGround) {
			yVel -= yAcc * 22;
		}
		
		xVel *= .9;
		yVel += yAcc;
		
		x += xVel;
		
		rect = new Rectangle(Main.round(x * Tile.tileMultiplier), Main.round(y * Tile.tileMultiplier), width * Tile.tileMultiplier, height * Tile.tileMultiplier);
		
		for (int ty = 0; ty < Tile.tiles.length; ty++) {
			for (int tx = 0; tx < Tile.tiles[0].length; tx++) {
				if (Tile.tiles[ty][tx] != null) {
					Rectangle tileRect = new Rectangle(tx * Tile.tileSize * Tile.tileMultiplier, ty * Tile.tileSize * Tile.tileMultiplier, Tile.tileSize * Tile.tileMultiplier, Tile.tileSize * Tile.tileMultiplier);
					if (Main.overlaps(rect, tileRect)) {
						if (xVel > 0) {
							xVel = 0;
							x = tileRect.x / Tile.tileMultiplier - width;
						}
						else if (xVel < 0) {
							xVel = 0;
							x = tileRect.x / Tile.tileMultiplier + width;
						}
					}
				}
			}
		}
		
		y += yVel;

		rect = new Rectangle(Main.round(x * Tile.tileMultiplier), Main.round(y * Tile.tileMultiplier), width * Tile.tileMultiplier, height * Tile.tileMultiplier);
		
		onGround = false;
		
		for (int ty = 0; ty < Tile.tiles.length; ty++) {
			for (int tx = 0; tx < Tile.tiles[0].length; tx++) {
				if (Tile.tiles[ty][tx] != null) {
					Rectangle tileRect = new Rectangle(tx * Tile.tileSize * Tile.tileMultiplier, ty * Tile.tileSize * Tile.tileMultiplier, Tile.tileSize * Tile.tileMultiplier, Tile.tileSize * Tile.tileMultiplier);
					if (Main.overlaps(rect, tileRect)) {
						if (yVel > 0) {
							yVel = 0;
							y = tileRect.y / Tile.tileMultiplier - height;
							onGround = true;
						}
						else if (yVel < 0) {
							yVel = 0;
							y = tileRect.y / Tile.tileMultiplier + Tile.tileSize;
						}
					}
				}
			}
		}
	}
}
