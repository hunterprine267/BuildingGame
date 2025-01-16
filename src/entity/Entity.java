package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Main;
import tile.Tile;

public class Entity {
	
	public double x = 0;
	public double y = 0;
	public double xVel = 0;
	public double yVel = 0;
	public double xAcc = 0;
	public double yAcc = 0;
	
	Rectangle rect = new Rectangle();
	public BufferedImage[] sprites;
	
	public int currentSprite = 0;
	public int currentAnimationSprite = 0;
	public int spriteTimer = 0;
	public int spriteWidth = 0;
	public int spriteHeight = 0;
	
	public void animate(int[] sprites, int time) {
		currentSprite = sprites[currentAnimationSprite];
		
		if (spriteTimer > time) {
			currentAnimationSprite++;
			time = 0;
		}
		
		if (currentAnimationSprite == sprites.length) {
			currentAnimationSprite = 0;
		}
		
		spriteTimer++;
	}
	
	public void draw(Graphics2D g2) {
		spriteWidth = sprites[currentSprite].getWidth();
		spriteHeight = sprites[currentSprite].getHeight();
		
		g2.drawImage(sprites[currentSprite], Main.round(x * Tile.tileMultiplier), Main.round(y * Tile.tileMultiplier), spriteWidth * Tile.tileMultiplier, spriteHeight * Tile.tileMultiplier, null);
		
		g2.setColor(Color.red);
		//g2.draw(rect);
	}
}