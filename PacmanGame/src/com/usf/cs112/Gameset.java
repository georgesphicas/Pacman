package com.usf.cs112;

import java.awt.HeadlessException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Gameset {
	 
	public static void main(String[] args) {	
		
		System.out.println("Enter your name, player: ");
		
		try (Scanner in = new Scanner(System.in)) {
			String name = in.next();
			
			JFrame frame = new JFrame();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setTitle("Professor Pac-Man");
			
			ScreenSetting screen = new ScreenSetting(name);
			
			frame.add(screen);
			frame.pack(); //adjusts the window to the correct size of the layout and subcomponents
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			screen.setupDots();
			screen.startThread();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		
		
	}
}


