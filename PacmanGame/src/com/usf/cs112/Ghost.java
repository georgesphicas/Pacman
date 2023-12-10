package com.usf.cs112;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ghost extends Character {

	ScreenSetting ss;
	
	public Ghost(ScreenSetting ss) {
		
		this.ss = ss;
		hitBox = new Rectangle(8, 8, 28, 28);	
		setDefaultVals();
		loadGhostImage();
		
	}
	
	public void setDefaultVals() {
		
		this.x = 300;
		this.y = 300;
		this.speed = 2;
		this.name = "Ghost";
		this.direction = "right";
		this.lives = 1;
	}
	/*
	 * load ghosts, Different color ghosts can be randomly generated based on a random integer method
	 */
	public void loadGhostImage() {
		
		Random rand = new Random();
		int randInt = rand.nextInt(2);
		
		try {

			if(randInt == 0)
			{
				ghost = ImageIO.read(getClass().getResourceAsStream("/Pac/ghost1.png"));
			}
			if(randInt == 1)
			{
				ghost = ImageIO.read(getClass().getResourceAsStream("/Pac/ghost3.png"));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	/*
	 * similar movement setup to Pacman(player), but direction only changes once ghost collides with a wall
	 */
	public void update() {
		
		collision = false;
		ss.cc.checkBlock(this);
		
		if(collision == true) {
			
			switchDirection();
		}
		
		if(collision == false) {
			switch(direction) {
			case "up":
				y -= speed;	
				hitBox_y -= speed;
				break;
			case "down":
				y += speed;
				hitBox_y += speed;
				break;
			case "left":
				x -= speed; 
				hitBox_x -= speed;
				break;
			case "right":
				x += speed;	
				hitBox_x += speed;
				break;
				
			}
	}
}
	
/*
 * determines the next direction the ghost will move
 * prior to this method call, a collision must be detected - once found, the direction will change
 * next movement is randomized using a randomly generated number corresponding to a direction
 */
	public void switchDirection() {
		
		Random rand = new Random();
		int randInt = rand.nextInt(4);
		if(randInt == 0)
		{
			direction = "left";
		}
		if(randInt == 1)
		{
			direction = "right";
		}
		if(randInt == 2)
		{
			direction = "up";
		}
		if(randInt == 3)
		{
			direction = "down";
		}
		
	}
	
	public void draw(Graphics2D g2d)  {
		
		BufferedImage ghosty = ghost;
		g2d.drawImage(ghosty, x, y, ss.tileSize, ss.tileSize, null);
	}
}
