package com.sqh.tool.algorithm.sort;

import java.util.List;

import com.sqh.test.utils.IntArrayGenerator;
import com.sqh.tool.algorithm.heap.DefaultMinHeap;
import com.sqh.tool.algorithm.heap.Heap;

public class HeapSort<T> implements Sort<T> {

	@Override
	public List<T> sort(List<T> datas) {
		
		return null;
	}

	public static void main(String[] args){
//		int[] a = new int[]{31, 41, 59, 26, 53, 58, 97};
//		Heap<Integer> heap = new DefaultMinHeap<Integer>(7);
//		for(int i=0; i<a.length; i++){
//			heap.insert(new Integer(a[i]));
//		}
//		
//		heap.show();
//		System.out.println("=============================");
//		while(!heap.isEmpty()){
//			Integer aInteger = heap.deleteMin();
//			System.out.println(aInteger);
//		}
		
		int[] a = IntArrayGenerator.getBigRandIntArrayFromFile("D:/workspaces/eclipse/Algorithm/src/main/resources/sort/bigRandInt_100000.txt");
		long startTime = System.currentTimeMillis();
//		for(int i=0; i<10; i++){
			testOne(a);
//		}
		System.out.println("cost" + (System.currentTimeMillis() - startTime));
	}
	
	public static void testOne(int[] a){
		Heap<Integer> heap = new DefaultMinHeap<Integer>(100000);
		for(int i=0; i<a.length; i++){
			heap.insert(new Integer(a[i]));
		}
		
		int i = 0;
		while(!heap.isEmpty() && i < 50){
			Integer aInteger = heap.deleteMin();
			System.out.println(aInteger);
			i++;
		}
	}
}
