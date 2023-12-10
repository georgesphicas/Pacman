package com.usf.cs112;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class BlockManager {

	ScreenSetting ss;
	Block[] block;
	public int levelBlockNum[][];
	
	public BlockManager(ScreenSetting ss) {
		
		this.ss = ss;
		block = new Block[30]; //amount of blocks
		levelBlockNum = new int[ss.maxScreenColumn][ss.maxScreenRow];
		loadBlockImage();
		loadMap();
	}
	/*
	 * importing custom block images: wall(1,2,3,4) represents one block type and each rotation of the image, then topper(1,2,3,4)... etc.
	 */
	public void loadBlockImage() {
	 
		try {

			block[0] = new Block();
			block[0].image = ImageIO.read(getClass().getResourceAsStream("/Block/wall1.png"));
			block[1] = new Block();
			block[1].image = ImageIO.read(getClass().getResourceAsStream("/Block/wall2.png"));
			block[2] = new Block();
			block[2].image = ImageIO.read(getClass().getResourceAsStream("/Block/wall3.png"));
			block[3] = new Block();
			block[3].image = ImageIO.read(getClass().getResourceAsStream("/Block/wall4.png"));
			
			block[4] = new Block();
			block[4].image = ImageIO.read(getClass().getResourceAsStream("/Block/topper1.png"));
			block[5] = new Block();
			block[5].image = ImageIO.read(getClass().getResourceAsStream("/Block/topper2.png"));
			block[6] = new Block();
			block[6].image = ImageIO.read(getClass().getResourceAsStream("/Block/topper3.png"));
			block[7] = new Block();
			block[7].image = ImageIO.read(getClass().getResourceAsStream("/Block/topper4.png"));
			
			block[8] = new Block();
			block[8].image = ImageIO.read(getClass().getResourceAsStream("/Block/joint1.png"));
			block[9] = new Block();
			block[9].image = ImageIO.read(getClass().getResourceAsStream("/Block/joint2.png"));
			block[10] = new Block();
			block[10].image = ImageIO.read(getClass().getResourceAsStream("/Block/join3.png"));
			block[11] = new Block();
			block[11].image = ImageIO.read(getClass().getResourceAsStream("/Block/joint4.png"));
			
			block[12] = new Block();
			block[12].image = ImageIO.read(getClass().getResourceAsStream("/Block/jpoint1.png"));
			block[13] = new Block();
			block[13].image = ImageIO.read(getClass().getResourceAsStream("/Block/jpoint2.png"));
			block[14] = new Block();
			block[14].image = ImageIO.read(getClass().getResourceAsStream("/Block/jpoint3.png"));
			block[15] = new Block();
			block[15].image = ImageIO.read(getClass().getResourceAsStream("/Block/jpoint4.png"));
			
			block[16] = new Block();
			block[16].image = ImageIO.read(getClass().getResourceAsStream("/Block/hall1.png"));
			block[17] = new Block();
			block[17].image = ImageIO.read(getClass().getResourceAsStream("/Block/hall2.png"));
			
			block[18] = new Block();
			block[18].image = ImageIO.read(getClass().getResourceAsStream("/Block/square.png"));
			
			block[19] = new Block();
			block[19].image = ImageIO.read(getClass().getResourceAsStream("/Block/corner1.png"));
			block[20] = new Block();
			block[20].image = ImageIO.read(getClass().getResourceAsStream("/Block/corner2.png"));
			block[21] = new Block();
			block[21].image = ImageIO.read(getClass().getResourceAsStream("/Block/corner3.png"));
			block[22] = new Block();
			block[22].image = ImageIO.read(getClass().getResourceAsStream("/Block/corner4.png"));
			
			block[23] = new Block();
			block[23].image = ImageIO.read(getClass().getResourceAsStream("/Block/empty.png"));
			
			/*
			 * giving collision to all of the blocks except for "Empty" block
			 */
			for(int x = 0; x < 23; x++) {
				block[x].collision = true;
			}
			block[23].collision = false;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 *iterating through the map text file in order to place the correct blocks in the correct positions
	 *each number from the file corresponds to a different kind of object 
	 */
	public void loadMap() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/Level/map6.txt");//import level txt file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < ss.maxScreenColumn && row < ss.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < ss.maxScreenColumn ) {
					
					String numbers[] = line.split(" "); //splitting a line to receive the numbers one by one, stored in array
					
					int num = Integer.parseInt(numbers[col]);
					
					levelBlockNum[col][row] = num;
					col++;
				}
				if(col == ss.maxScreenColumn) {
					col = 0;
					row++;
				}
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2d) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while(col < ss.maxScreenColumn && row < ss.maxScreenRow) {
			
			int blockNum = levelBlockNum[col][row];
			g2d.drawImage(block[blockNum].image, x, y, ss.tileSize, ss.tileSize, null);
			col++;
			x += ss.tileSize;
			
			if(col == ss.maxScreenColumn ) {
				col = 0;
				x = 0;
				row++;
				y += ss.tileSize;
				
			}
			
		}
		

	}
	
}
