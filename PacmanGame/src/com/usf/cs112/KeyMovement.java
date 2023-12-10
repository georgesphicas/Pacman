package com.usf.cs112;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * tracks key movement in order for the Player class to take this information and convert it into actual movement
 */
public class KeyMovement implements KeyListener{

	public boolean up, down, left, right;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			
			up = true;
		}
		if(key == KeyEvent.VK_DOWN) {
			
			down = true;
		}
		if(key == KeyEvent.VK_LEFT) {
			
			left = true;
		}
		if(key == KeyEvent.VK_RIGHT) {
			
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			
			up = false;
		}
		if(key == KeyEvent.VK_DOWN) {
			
			down = false;
		}
		if(key == KeyEvent.VK_LEFT) {
			
			left = false;
		}
		if(key == KeyEvent.VK_RIGHT) {
			
			right = false;
		}
	}

}
