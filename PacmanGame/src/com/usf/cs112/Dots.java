package com.usf.cs112;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * dot creation to be called on by dotplacer, each dot given a default position and hitbox size
 */
public class Dots {

	public BufferedImage dots;
	public String name = "Dots";
	public boolean collision = false;
	public int x, y;
	public Rectangle hitBox = new Rectangle(0, 0, 48, 48); //dot hit-box is as a big as a tile to make it easier to touch each box
	public int hitBox_x = 0;
	public int hitBox_y = 0;
	
	public Dots() {
		
		try {
			
			dots = ImageIO.read(getClass().getResourceAsStream("/Dots/dots.png"));
			
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		collision = true;
	}

	public void setCoordinates(int newX, int newY) {
		
		x = newX;
		y = newY;
		
	}
	
	public void draw(Graphics2D g2d, ScreenSetting ss) {
		
		int scale = 48;
		g2d.drawImage(dots, scale * x, scale * y, ss.tileSize, ss.tileSize, null);

		
	}
	
}
