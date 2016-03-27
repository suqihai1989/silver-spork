package com.sqh.tool.algorithm.genetic.example.mapsearch;

import com.sqh.tool.algorithm.genetic.base.Genic;
import com.sqh.tool.algorithm.genetic.base.GenicIteration;

public class MapSearchGenicIteration extends GenicIteration {

	private static int[][] map = {
			{1, 1, 1, 1, 1, 1},
			{8, 0, 0, 1, 0, 1},
			{1, 0, 0, 1, 0, 1},
			{0, 1, 0, 0, 0, 5},
			{1, 1, 1, 0, 1, 1}
	};
	private static int[] start = {1, 0};
	private static int[] end   = {3, 4};
	private static int   maxHeight = 5;
	private static int   maxWidth  = 6;
	
	public MapSearchGenicIteration(int genicNum, int genicLength) {
		super(genicNum, genicLength);
	}
	
	private int[] getStop(Genic genic){
		int[] path = genic.getGenic();
		int[] result = new int[2];
		int temp = 0;
		result[0] = start[0];
		result[1] = start[1];
		for(int i = 0; i < path.length; i++){
			if(i%2 == 0){
				temp += path[i];
			}else{
				temp = 2*temp + path[i];
				switch(temp){
					case 0:      //North
						if(result[0] - 1 >= 0 && map[result[0] - 1][result[1]] == 0){
							result[0] -= 1;
						}
						break;
					case 1:      //East
						if(result[1] + 1 < maxWidth && map[result[0]][result[1] + 1] == 0){
							result[1] += 1;
						}
						break;
					case 2:      //South
						if(result[0] + 1 < maxHeight && map[result[0] + 1][result[1]] == 0){
							result[0] += 1;
						}
						break;
					case 3:      //West
						if(result[1] - 1 >= 0 && map[result[0]][result[1] - 1] == 0){
							result[1] -= 1;
						}
						break;
				}
				temp = 0;
			}
		}
		return result;
	}

	@Override
	protected void testAdaptation(Genic genic) {
		int[] stop = getStop(genic);
		int height = Math.abs(end[0] - stop[0]);
		int width  = Math.abs(end[1] - stop[1]);
		
		genic.setAdaptation(1.0/(height + width + 1));
	}
	
	private int[] showPath(Genic genic){
		int[] path = genic.getGenic();
		int[] result = new int[2];
		int temp = 0;
		result[0] = start[0];
		result[1] = start[1];
		for(int i = 0; i < path.length; i++){
			if(i%2 == 0){
				temp += path[i];
			}else{
				temp = 2*temp + path[i];
				switch(temp){
					case 0:      //North
						if(result[0] - 1 >= 0 && map[result[0] - 1][result[1]] == 0){
							result[0] -= 1;
						}
						System.out.print("上    ");
						break;
					case 1:      //East
						if(result[1] + 1 < maxWidth && map[result[0]][result[1] + 1] == 0){
							result[1] += 1;
						}
						System.out.print("右    ");
						break;
					case 2:      //South
						if(result[0] + 1 < maxHeight && map[result[0] + 1][result[1]] == 0){
							result[0] += 1;
						}
						System.out.print("下    ");
						break;
					case 3:      //West
						if(result[1] - 1 >= 0 && map[result[0]][result[1] - 1] == 0){
							result[1] -= 1;
						}
						System.out.print("左    ");
						break;
				}
				temp = 0;
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		MapSearchGenicIteration map = new MapSearchGenicIteration(50, 18);
		map.iteration();
		Genic genic = null;
		for(Genic g : map.getInitGenics()){
			if(g.getAdaptation() == 1){
				genic = g;
				break;
			}
		}
		if(genic != null){
			int[] stop = map.showPath(genic);
			System.out.println("stop=[" + stop[0] + ", " + stop[1] + "]");
		}
	}
}
