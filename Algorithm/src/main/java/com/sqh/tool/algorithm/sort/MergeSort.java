package com.sqh.tool.algorithm.sort;

import java.util.List;

import com.sqh.test.utils.IntArrayGenerator;

/**
 * 归并排序以O(N*logN)最坏情形运行时间。<br/>
 * 这个算法中基本的操作是合并两个已排序的表，因为这两个表是已排序的。
 * @author Administrator
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> implements Sort<T>{
	public static void sort(int[] input, int[] result, int left, int right){
		if(left < right){
			int middle = (left + right)/2;
			sort(input, result, left, middle);
			sort(input, result, middle + 1, right);
			merge(input, result, left, middle + 1, right);
		}
	}
	
	private static void merge(int[] input, int[] result, int left, int rightFirst, int right) {
		int k = left;
		int i = left;
		int j = rightFirst;
		
		while(i < rightFirst && j < right + 1){
			if(input[i] <= input[j]){
				result[k] = input[i];
				i++;
			}else{
				result[k] = input[j];
				j++;
			}
			k++;
		}
		
		while(i < rightFirst){
			result[k] = input[i];
			k++; i++;
		}
		while(j < right + 1){
			result[k] = input[j];
			k++; j++;
		}
		
		for(i=left; i <= right; i++){
			input[i] = result[i];
		}
	}

	public static int[] sort(int[] input){
		int[] result = new int[input.length];
		sort(input, result, 0, input.length - 1);
		return result;
	}
	
	@Override
	public List<T> sort(List<T> datas) {
		return null;
	}
	
	public static void main(String[] args){
//		int[] a = new int[]{24, 13, 26, 1, 2, 27, 38, 15};
		int[] a = IntArrayGenerator.getBigRandIntArrayFromFile("D:/workspaces/eclipse/Algorithm/src/main/resources/sort/bigRandInt_100000.txt");
		long startTime = System.currentTimeMillis();
		int[] result = MergeSort.sort(a);
//		for(int i=0; i<result.length; i++){
//			System.out.print(result[i] + " ");
//		}
		long cost = System.currentTimeMillis() - startTime;
		for(int i=0; i<50; i++){
			System.out.println(result[i]);
		}
		System.out.println("cost = " + cost );
	}

}
