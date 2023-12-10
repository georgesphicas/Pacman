package com.usf.cs112;

public class DotPlacer {

	ScreenSetting ss;
	
	public DotPlacer(ScreenSetting ss) {
		
		this.ss = ss;
	}
	/*
	 * individually placing each dot as its own object in order for each dot to be able to be set to null once Pacman's hitbox intersects the dot hitbox
	 */
	public void placeDots() {

		for (int x = 0; x < 9; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(1, x + 3);
			
		}
		for (int x = 9; x < 12; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(2, x - 7);
			
		}
		ss.dot[12] = new Dots();
		ss.dot[12].setCoordinates(2, 9);
		
		ss.dot[13] = new Dots();
		ss.dot[13].setCoordinates(2, 11);
		
		ss.dot[14] = new Dots();
		ss.dot[14].setCoordinates(3, 2);
		
		for (int x = 15; x < 23; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(3, x - 11);
		}
		
		for (int x = 23; x < 32; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(4, x - 21);
		}
		
		for (int x = 32; x < 37; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(5, x - 31);
		}
		
		for (int x = 37; x < 39; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(5, x - 29);
		}
		
		for (int x = 39; x < 45; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(6, x - 38);
		}
		
		for (int x = 45; x < 48; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(6, x - 37);
		}
		
		for (int x = 48; x < 51; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(7, x - 47);
		}
		
		for (int x = 51; x < 58; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(7, x - 46);
		}
		
		ss.dot[58] = new Dots();
		ss.dot[58].setCoordinates(8,1);
		
		ss.dot[59] = new Dots();
		ss.dot[59].setCoordinates(8,3);
		
		ss.dot[60] = new Dots();
		ss.dot[60].setCoordinates(8,6);
		
		ss.dot[61] = new Dots();
		ss.dot[61].setCoordinates(8,7);
		
		ss.dot[62] = new Dots();
		ss.dot[62].setCoordinates(8,10);
		
		ss.dot[63] = new Dots();
		ss.dot[63].setCoordinates(8,11);
		
		for (int x = 64; x < 67; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(9, x - 63);
		}
		
		for (int x = 67; x < 70; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(9, x - 62);
		}
		
		ss.dot[70] = new Dots();
		ss.dot[70].setCoordinates(9,10);
		
		ss.dot[71] = new Dots();
		ss.dot[71].setCoordinates(9,11);

		for (int x = 72; x < 83; x++) {
			
			ss.dot[x] = new Dots();
			ss.dot[x].setCoordinates(10, x - 71);
		}
		
	}


	
}
