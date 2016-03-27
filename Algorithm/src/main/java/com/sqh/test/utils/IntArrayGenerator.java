package com.sqh.test.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 这个类主要用来生成指定数量的随机数(数的最大值为num*2)<br/>
 * 并输出到指定的文件中。
 * @author Administrator
 */
public class IntArrayGenerator {
	
	public static boolean gererate(String filePath, int num){
		Random random = new Random(System.currentTimeMillis());
		
		Set<Integer> alreadySet = new HashSet<Integer>();
		List<Integer> randList = new ArrayList<Integer>();
		int max = 2*num;
		for(int i = 0; i < num; i++){
			Integer randInteger = random.nextInt(max);
			if(alreadySet.contains(randInteger)){
				i--;
				continue;
			}
			
			alreadySet.add(randInteger);
			randList.add(randInteger);
		}
		
		System.out.println(randList.size());
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
			for(Integer randInteger : randList){
				writer.append(randInteger.toString()).append(' ');
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public static int[] getBigRandIntArrayFromFile(String filePath){
		String file = filePath;
		if(file == null){
			file = "D:/workspaces/eclipse/Algorithm/src/main/resources/sort/bigRandInt.txt";
		}
		
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			String temp = null;
			while((temp = reader.readLine()) != null){
				sb.append(temp);
			}
			temp = null;
			
			String[] splits = sb.toString().trim().split(" ");
			int[] ret = new int[splits.length];
			for(int i = 0; i < splits.length; i++){
				ret[i] = Integer.parseInt(splits[i]);
			}
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		IntArrayGenerator.gererate("D:/workspaces/eclipse/Algorithm/src/main/resources/sort/bigRandInt_100000.txt", 100000);
//		System.out.println(IntArrayGenerator.getBigRandIntArrayFromFile(null));
	}
}
