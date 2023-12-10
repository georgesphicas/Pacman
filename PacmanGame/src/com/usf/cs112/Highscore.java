package com.usf.cs112;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * class reads from the savedScores file in order to compare the current players scores to those of past players
 * score is inserted based on where it stands amongst the other scores
 * should iterate from highest score to bottom, so will insert itself itself at the first instance the score is higher than another
 */

public class Highscore {

	ScreenSetting ss;
	String name;
	int score;
	boolean isTopTen = false;
	File savedScores;
	
	
	public Highscore(ScreenSetting ss, String name, int score, File savedScores) {
		
		this.ss = ss;
		this.name = name;
		this.score = score;
		this.savedScores = savedScores;
	}
	
	public void compareScore() {
			
		List<Integer> allScores = new ArrayList<Integer>();
		File file = new File("leaderboard.txt");
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		        allScores.add(Integer.parseInt(text));
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}	
			
		}
			
		
	
	
	public void saveScores() {
		
		if (isTopTen == true) {
		try {
			FileWriter leaderboard = new FileWriter("leaderboard.txt");
			leaderboard.write(score);
			leaderboard.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		
	}
	
	
	
}
