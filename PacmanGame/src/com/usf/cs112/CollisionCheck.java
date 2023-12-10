package com.usf.cs112;

public class CollisionCheck {
	
	ScreenSetting ss;

	public CollisionCheck(ScreenSetting ss) {
		this.ss = ss;
	}
	
	/*
	 * checking whether or not the character is hitting the bounds
	 * comparing the position two sides of the hitBox rectangle depending on the given direction in order to determine if the sides are colliding with a wall
	 */
	public void checkBlock(Character character) {
		
		int characterLeftX = character.x + character.hitBox.x;
		int characterRightX = character.x + character.hitBox.x + character.hitBox.width;
		int characterTopY = character.y + character.hitBox.y;
		int characterBottomY = character.y + character.hitBox.y + character.hitBox.height;
		
		int characterLeftCol = characterLeftX / ss.tileSize;
		int characterRightCol = characterRightX / ss.tileSize;
		int characterTopRow = characterTopY / ss.tileSize;
		int characterBottomRow = characterBottomY / ss.tileSize;
		
		int blockNum1, blockNum2;
		
		switch(character.direction) {
		case "up":
			characterTopRow = (characterTopY - character.speed) / ss.tileSize;
			blockNum1 = ss.blockM.levelBlockNum[characterLeftCol][characterTopRow];
			blockNum2 = ss.blockM.levelBlockNum[characterRightCol][characterTopRow];
			if(ss.blockM.block[blockNum1].collision == true || ss.blockM.block[blockNum2].collision == true) {
				character.collision = true;
			}		
			break;
		case "down":
			characterBottomRow = (characterBottomY + character.speed) / ss.tileSize;
			blockNum1 = ss.blockM.levelBlockNum[characterLeftCol][characterBottomRow];
			blockNum2 = ss.blockM.levelBlockNum[characterRightCol][characterBottomRow];
			if(ss.blockM.block[blockNum1].collision == true || ss.blockM.block[blockNum2].collision == true) {
				character.collision = true;
			}
			break;
		case "left":
			characterLeftCol = (characterLeftX - character.speed) / ss.tileSize;
			blockNum1 = ss.blockM.levelBlockNum[characterLeftCol][characterTopRow];
			blockNum2 = ss.blockM.levelBlockNum[characterLeftCol][characterBottomRow];
			if(ss.blockM.block[blockNum1].collision == true || ss.blockM.block[blockNum2].collision == true) {
				character.collision = true;
			}
			break;
		case "right":
			characterRightCol = (characterRightX + character.speed) / ss.tileSize;
			blockNum1 = ss.blockM.levelBlockNum[characterRightCol][characterTopRow];
			blockNum2 = ss.blockM.levelBlockNum[characterRightCol][characterBottomRow];
			if(ss.blockM.block[blockNum1].collision == true || ss.blockM.block[blockNum2].collision == true) {
				character.collision = true;
			}
			break;
		}
	}
	
	/*
	 * Seeing whether or not pacman is colliding with a dot
	 * boolean player determines whether or not Pacman was passed into checkDots(not a ghost)
	 */
	public int checkDots(Character character, boolean isPac) {
		
		int index = 150; //unreachable index
		
		for(int i = 0; i < ss.dot.length; i++) {
			
			if(ss.dot[i] != null) {
				 
				character.hitBox.x = character.x + character.hitBox.x;
				character.hitBox.y = character.y + character.hitBox.y;
				
				ss.dot[i].hitBox.x = ss.dot[i].x + ss.dot[i].hitBox.x;
				ss.dot[i].hitBox.y = ss.dot[i].y + ss.dot[i].hitBox.y;
				
				switch(character.direction) {
				case "up":
					character.hitBox.y -= character.speed;
					if(ss.player.hitBox.intersects(ss.dot[i].hitBox)) {
						if(ss.dot[i].collision == true) {
							character.collision = true;
						}
						if(isPac == true) {
							index = i;
						}
					}
					break;
				case "down":
					character.hitBox.y += character.speed;
					if(ss.player.hitBox.intersects(ss.dot[i].hitBox)) {
						if(ss.dot[i].collision == true) {
							character.collision = true;
						}
						if(isPac == true) {
							index = i;
						}
					}
					break;
				case "left":
					character.hitBox.x -= character.speed;
					if(ss.player.hitBox.intersects(ss.dot[i].hitBox)) {
						if(ss.dot[i].collision == true) {
							character.collision = true;
						}
						if(isPac == true) {
							index = i;
						}
					}
					break;
				case "right":
					character.hitBox.x += character.speed;
					if(ss.player.hitBox.intersects(ss.dot[i].hitBox)) {
						if(ss.dot[i].collision == true) {
							character.collision = true;
						}
						if(isPac == true) {
							index = i;
						}
					}
					break;
				}
			    character.hitBox.x = character.hitBox_x;
				character.hitBox.y = character.hitBox_y;
				
				ss.dot[i].hitBox.x = ss.dot[i].hitBox_x;
				ss.dot[i].hitBox.x = ss.dot[i].hitBox_y;
				
			}	
		}
		
		
		return index;
		
	}
	/*
	 * given that player hitbox and ghost hitbox are set as rectangles, .intersects method should determine when a collision takes place
	 */
	public void ghostCollisionCheck(Character pac, Character ghost) {
		
		if(pac.hitBox.intersects(ghost.hitBox)) {
			ss.player.lives--;
		}
	}
}
