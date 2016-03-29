package com.sqh.tool.algorithm.math;

import java.util.Random;

/**
 * 对[m,n] 正整数重新随机打乱生成一个数组。 
 * @author Administrator
 */
public class RandArrayIntGenerator {
	
	/**
	 * 这个算法，每次随机到一个值后，都跟之前已经随机到的值依次比较发现是个新的之后，再添加到数组之中。
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[] compareWithBefore(int m, int n){
		int length = n - m + 1;
		int[] ret = new int[length];
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < length; i++){
			while(true){
				int nextRand = random.nextInt(length) + m;
				boolean isExist = false;
				for(int j = 0; j < i; j++){  //如果之前已经随机出来过，则继续生成新的随机数。
					if(nextRand == ret[j]){
						isExist = true;
						break;
					}
				}
				if(!isExist){
					ret[i] = nextRand;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * 这个算法通过使用一个数据来标记已经随机出来过的值，从而避免跟之前随机出来过的值进行比较来判断是否新值。
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[] otherArrayToTagBefore(int m, int n){
		int length = n - m + 1;
		int[] ret = new int[length];
		int[] tag = new int[length];
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < length; i++){
			while(true){
				int nextTag = random.nextInt(length);
				if(tag[nextTag] == 0){ //这是没出现过的随机值。
					ret[i] = nextTag + m;
					tag[nextTag] = 1;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * 这种算法完全避免了rand出来的随机值会冲突，不会有多次生成随机值才能得到新值的情况发生。
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[] avoidGenerateSameRand(int m, int n){
		int length = n - m + 1;
		int[] ret = new int[length];
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < length; i++){
			ret[i] = m + i;
		}
		
		for(int i = 1; i < length; i++){
			int nextRand = random.nextInt(i);
			
			int temp = ret[nextRand];
			ret[nextRand] = ret[i];
			ret[i] = temp;
		}
		return ret;
	}

//	public static void main(String[] args){
//		int[] ret = RandArrayIntGenerator.compareWithBefore(4, 10);
//		for(int i : ret){
//			System.out.print(i);
//			System.out.print('\t');
//		}
//		
//		System.out.println("");
//		ret = RandArrayIntGenerator.otherArrayToTagBefore(4, 10);
//		for(int i : ret){
//			System.out.print(i);
//			System.out.print('\t');
//		}
//		
//		System.out.println("");
//		ret = RandArrayIntGenerator.avoidGenerateSameRand(4, 10);
//		for(int i : ret){
//			System.out.print(i);
//			System.out.print('\t');
//		}
//	}
	
	public static void main(String[] args){
		testCost(1, 5000);
	}
	
	public static void testCost(int m, int n){
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10; i ++){
			RandArrayIntGenerator.compareWithBefore(m, n);
		}
		long oneEnd = System.currentTimeMillis();
		System.out.println("compareWithBefore cost=" + (oneEnd - startTime));
		
		for(int i = 0; i < 10; i ++){
			RandArrayIntGenerator.otherArrayToTagBefore(m, n);
		}
		long twoEnd = System.currentTimeMillis();
		System.out.println("otherArrayToTagBefore cost=" + (twoEnd - oneEnd));
		
		for(int i = 0; i < 10; i ++){
			RandArrayIntGenerator.avoidGenerateSameRand(m, n);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("avoidGenerateSameRand cost=" + (endTime - twoEnd));
	}
}
