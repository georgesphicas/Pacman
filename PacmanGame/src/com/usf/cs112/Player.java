package com.usf.cs112;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Character {
		
		ScreenSetting ss;
		KeyMovement keyM;
		
		public Player(ScreenSetting ss, KeyMovement keyM) {
			
			this.ss = ss;
			this.keyM = keyM;
			
			hitBox = new Rectangle(8, 8, 28, 28); //shrink the hitbox slightly, block are 48 x 48, make it easier to fit through one block gaps
			
			setDefaultVals();
			loadPlayerImage();
			
		}
		
		public void setDefaultVals() {
			
			name = "Pacman";
			x = 200;
			y = 200;
			hitBox_x = 8;
			hitBox_y = 8;
			speed = 4;
			direction = "right";
			lives = 3;
		}
		
		public void loadPlayerImage() {
			
			try {

				pacU1 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacU1.png"));
				pacU2 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacU2.png"));
				pacD1 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacD1.png"));
				pacD2 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacD2.png"));
				pacL1 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacL1.png"));
				pacL2 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacL2.png"));
				pacR1 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacR1.png"));
				pacR2 = ImageIO.read(getClass().getResourceAsStream("/Pac/pacR2.png"));
			
			}
			
			catch(IOException e) {
				
				e.printStackTrace();
			}
		}
		
		public int getLives() {
			
			return lives;
		}
		
		public void takeDamage() {
			
			lives--;
		}
		/*
		 * for every frame update is called, the Animation counter is increased
		 * updates at 60 frames per second, every n frames the animation cycles 
		 */
		public void update() {
			
			if(keyM.up == true || keyM.down == true || keyM.left == true || keyM.right == true) {
				
			if(keyM.up == true) {
				 direction = "up";
				  
			 }
			 else if(keyM.down == true) {
				 direction = "down";
			
			 }
			 else if(keyM.left == true) {
				 direction = "left";
			
			 }
			 else if(keyM.right == true) {
				 direction = "right";
				 
			 }
			
			collision = false;
			
			ss.cc.checkBlock(this);
			
			ss.cc.ghostCollisionCheck(ss.player, ss.ghost);
			
			int dotIndex = ss.cc.checkDots(this, true);
			
			eatDots(dotIndex);
			
			if(collision == false) {
				switch(direction) {
				case "up":
					y -= speed;
				//	hitBox_y -= speed;
					break;
				case "down":
					y += speed;
				//	hitBox_y += speed;
					break;
				case "left":
					x -= speed; 
				//	hitBox_x -= speed;
					break;
				case "right":
					x += speed;	
				//	hitBox_x += speed;
					break;
					
				}
			}
			/*
			 * by setting animCounter if statement condition to 12, every 12 frames pacman switches to the next animation
			 */
			 animCounter++;
			 if(animCounter > 12) {
				 if(animNum == 1) {
					 animNum = 2;
			 }
			 else if(animNum == 2) {
				 animNum = 1;
			 }
			 animCounter = 0;
			 }
			 }
		}
		
		public void eatDots(int x) {
			
			if(x != 150) {
				
				ss.dot[x] = null;
				ss.score++;
				
				//System.out.println(ss.score);
				
			}
		}
		
		/*
		 * given the direction, cycling through the proper image to draw
		 * then keeping track of which frame the animation is on, cycling between open and closed mouth Pacman
		 */
		public void draw(Graphics2D g2d) {
		
			 BufferedImage image = null;
			 switch(direction) {
			 case "up":
				if(animNum == 1)
			 	image = pacU1;
				if(animNum == 2)
				image = pacU2;
			 	break;
			 case "down":
				    if(animNum == 1)
				 	image = pacD1;
				    if(animNum == 2)
					image = pacD2;
				 	break;
			 case "left":
				    if(animNum == 1)
				 	image = pacL1;
				    if(animNum == 2)
					image = pacL2;
				 	break;
			 case "right":
				    if(animNum == 1)
				 	image = pacR1;
				    if(animNum == 2)
					image = pacR2;
				 	break;
			 }
			 g2d.drawImage(image, x, y, ss.tileSize, ss.tileSize, null);
		}
}
