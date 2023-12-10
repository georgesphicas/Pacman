package com.usf.cs112;

import java.awt.Color;
import java.awt.Graphics2D;

public class Scoreboard {

	ScreenSetting ss;
	int score;
	int lives;
	 
	public Scoreboard(ScreenSetting ss) {
		
		this.ss = ss;
		
	}
	
	public void update() {
		
		score = ss.score;
		lives = ss.player.getLives();
		
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.drawString("Score: " + score, 40, 25);
		
		g2d.setColor(Color.white);
		g2d.drawString("Lives: " + lives, 485, 25);
		
	}
	
}
