package com.sqh.tool.algorithm.sort;

import com.sqh.test.utils.IntArrayGenerator;

/**
 * 插入排序
 * 包括: 直接插入排序，二分插入排序（又称折半插入排序），链表插入排序，希尔排序（又称缩小增量排序）。属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置） 
 * @author suqihai
 */
public class InsertSort {
	public static final int ABSOLUTE_INSET = 0;
	public static int[] sort(int[] input, int type){
		if(input == null || input.length == 0){
			return null;
		}
		int length = input.length;
		for(int j = 1; j < length; j++){
			int key = input[j];
			int i = j-1;
			while(i >= 0 && input[i] > key){
				input[i+1] = input[i];
				i--;
			}
			input[i+1] = key;
		}
		return input;
	}
	
	public static int[] sort(int[] input){
		return sort(input, ABSOLUTE_INSET);
	}
	
	public static void main(String[] args){
//		int[] a = {5, 2, 4, 6, 1, 3};
		int[] a = IntArrayGenerator.getBigRandIntArrayFromFile("D:/workspaces/eclipse/Algorithm/src/main/resources/sort/bigRandInt_100000.txt");
//		for(int aa : a){
//			System.out.print(aa);
//		}
		long startTime = System.currentTimeMillis();
		a = InsertSort.sort(a);
//		System.out.println("");
//		for(int aa : a){
//			System.out.print(aa);
//		}
		long cost = System.currentTimeMillis() - startTime;
		for(int i=0; i<50; i++){
			System.out.println(a[i]);
		}
		
		System.out.println("cost = " + cost );
	}
}
