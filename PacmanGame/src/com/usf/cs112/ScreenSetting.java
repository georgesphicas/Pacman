package com.usf.cs112;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JPanel;

public class ScreenSetting extends JPanel implements Runnable{

	 final int originalTileSize = 16;
	 final int scale = 3; //scaling up the tiles
	 final int tileSize = originalTileSize * scale;
	 final int maxScreenColumn = 12;
	 final int maxScreenRow = 13;
	 final int screenWidth = tileSize * maxScreenColumn;
	 final int screenHeight = tileSize * maxScreenRow;
	 final int framesPerSecond = 60;
	 
	 BlockManager blockM = new BlockManager(this);
	 KeyMovement keyM = new KeyMovement();
	 Thread thread; //creating a thread in order to create a game loop
	 
	 public CollisionCheck cc = new CollisionCheck(this);
	 public DotPlacer dPlacer = new DotPlacer(this);
	 
	 public Player player = new Player(this, keyM);
	 public Ghost ghost = new Ghost(this);
	 public Ghost ghost2 = new Ghost(this);
	 public Ghost ghost3 = new Ghost(this);
	 
	 public Dots dot[] = new Dots[150]; //display up to 150 objects concurrently
	 
	 public int score = 0;
	 
	 public String name;
	 
	 public Scoreboard scoreboard = new Scoreboard(this);
	 
	 File savedScores = new File("savedScores.txt");
	 
	 public ScreenSetting(String name) {
		 
		 this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		 this.setBackground(Color.BLACK);
		 this.setDoubleBuffered(true); //improves performance
		 this.addKeyListener(keyM);
		 this.setFocusable(true);
		 this.name = name;
	 }
	 
	 public void setupDots() {
		 
		 dPlacer.placeDots();
	 }
	 
	 public void startThread() {
		 
		 thread = new Thread(this);
		 thread.start();
		 playMusic();
	 }
	 /*
	  * once the thread begins, the run method is called
	  */
	 public void run() {
		 
		 double drawRate = 1000000000/framesPerSecond; //determining the next draw time
		 double nextDrawTime = System.nanoTime() + drawRate;
		 
		 while(thread != null ) {
					 	 
			 player.update();
			 
			 ghost.update();
			 
			 ghost2.update();
			 
			 ghost3.update();
			 
			 scoreboard.update();
			 
			 /*
			  * if max dots is reached thread ends
			  */
			 if(score == 83) {
				 thread = null;
			 }
			 /*
			 if(player.lives < 0) {
				 thread = null;
			 }
			 */
			 
			 repaint();
			 
			 try {
				 
				 double timeLeft = nextDrawTime - System.nanoTime();
				 timeLeft = timeLeft / 1000000; //convert from nano to milliseconds
				 
				 if(timeLeft < 0) {
					 timeLeft = 0;
				 }
				 
				 Thread.sleep((long) timeLeft);
				 nextDrawTime += drawRate;
				 
			 }
			 catch(InterruptedException e) {
				 
				 e.printStackTrace();
			 }
		 
		 }
		 /*
		  * once the game ends, display the highscore leaderboard
		  */
		 if(thread == null)
		 {
			 Highscore highscore = new Highscore(this, name, score, savedScores);
		 }
	 }
	 
	 public void paintComponent(Graphics g) {
		 
		 super.paintComponent(g);
		 
		 Graphics2D g2d = (Graphics2D)g;
		 
		 blockM.draw(g2d); //putting this before player will make this layer go underneath the player
		 
		 //given the dots stored in the array, iterate through each to place them until there are no dots left to place

		 for(int x = 0; x < dot.length; x++) {
			 
			 if(dot[x] != null) {
				 
				 dot[x].draw(g2d, this);
			 }
		 }
			 
		 
		 player.draw(g2d);
		 
		 ghost.draw(g2d);
		 
		 ghost2.draw(g2d);
		 
		 ghost3.draw(g2d);
		 
		 scoreboard.draw(g2d);
		 
		 g2d.dispose();
		 
	 }
	 
		/*
		 * I made a Pac-man song from scratch to have as background music
		 * use Clip in order to allow the audio to loop repeatedly
		 */
		private void playMusic() {

			try {
			
			File musicPath = new File("pactrack3.wav");
			
			if(musicPath.exists()) {
			
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip pacSong = AudioSystem.getClip();
				pacSong.open(audioInput);	
				FloatControl gainControl = (FloatControl) pacSong.getControl(FloatControl.Type.MASTER_GAIN);	
				gainControl.setValue(-10.0f);
				pacSong.start();
				pacSong.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			else {
			
				System.out.println("Audio file not found");
			}
			
		}
			
			catch(Exception ex) {
			
				ex.printStackTrace();
			}

		}
}
