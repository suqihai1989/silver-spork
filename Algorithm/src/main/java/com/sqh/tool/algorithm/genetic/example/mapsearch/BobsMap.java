package com.sqh.tool.algorithm.genetic.example.mapsearch;

/**
 * 迷宫地图
 * @author Administrator
 */
public class BobsMap {
	private int[][] memory = new int[15][10];
	
	private int[][] map = {
		 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		 {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
		 {8, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
		 {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
		 {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
		 {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
		 {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
		 {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5},
		 {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
		 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	
	private final int mapHeight = 10;
	private final int mapWidth = 15;
	
	private final int startX = 14;
	private final int startY = 7;
	private final int endX = 0;
	private final int endY = 2;
	
	public BobsMap(){
		
	}
	
	public double testRoute(int[] path, BobsMap bobs){
		int posX = startX;
		int posY = startY;
		
		for(int dir = 0; dir < path.length; dir++){
			switch(path[dir]){
				case 0:   //North
					//check within bounds and that we can move
					if( ( (posY-1) < 0) || (map[posY-1][posX]) == 1){
						break;
					}else{
						posY -= 1;
					}
					break;
				case 1:   //South
					//check within bounds and that we can move
					if( ( (posY+1) >= mapHeight) || (map[posY+1][posX]) == 1){
						break;
					}else{
						posY += 1;
					}
					break;
				case 2:   //East
					//check within bounds and that we can move
					if( ( (posX+1) >= mapWidth) || (map[posY][posX+1]) == 1){
						break;
					}else{
						posX += 1;
					}
					break;
				case 3:   //West
					//check within bounds and that we can move
					if( ( (posX-1) < 0) || (map[posY][posX-1]) == 1){
						break;
					}else{
						posX -= 1;
					}
					break;
			}
			bobs.memory[posY][posX] = 1;
		}

		int	diffX = Math.abs(posX - endX);
		int diffY = Math.abs(posY - endY);
		
		return 1/(double)(diffX+diffY+1);
	}
}
