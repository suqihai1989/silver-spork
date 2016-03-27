package com.sqh.tool.algorithm.genetic.base;

import java.util.Random;

public class RandomUtils {
	private static Random random = new Random(System.currentTimeMillis());
	
	public static double randFloat0to1(){
		return random.nextDouble();
	}
	
	public static double randomFloat(){
		return random.nextFloat()*2 - 1;
	}
}
