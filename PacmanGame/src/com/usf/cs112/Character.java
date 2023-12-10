package com.usf.cs112;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * setting basic positional variables & image variables
 * to be used as a superclass for Pac-man and ghosts, and potentially any other entities i.e. another separate pacman
 */
public class Character {

	public int x, y;
	public int speed;
	public String name;
	public int lives;
	
	public BufferedImage pacU1, pacU2, pacD1, pacD2, pacL1, pacL2, pacR1, pacR2, ghost;
	public String direction;
	
	public int animCounter = 0;
	public int animNum = 1;
	
	public Rectangle hitBox; //creating a rectangle to store the hitbox of pacman
	public int hitBox_x, hitBox_y;
	public boolean collision = false;
}
	
