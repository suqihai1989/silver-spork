package com.sqh.tool.algorithm.heap.application;

import com.sqh.tool.algorithm.heap.DefaultMinHeap;
import com.sqh.tool.algorithm.heap.Heap;

public class IntegerHeap extends DefaultMinHeap<Integer> {

	public IntegerHeap(int capacity) {
		super(capacity);
	}

	public static void main(String[] args){
//		Integer[] array = new Integer[]{          150, 
//										80,               40, 
//									 30,    10,      70,       110, 
//							     100, 20, 90, 60,  50, 120, 140, 130};
//		Heap<Integer> heap = DefaultMinHeap.build(array, 100);
//		Integer min = heap.findMin();
//		System.out.println(min);
		
		Heap<Integer> heap = new IntegerHeap(100);
		Integer[] array = new Integer[]{          150, 
										80,               40, 
	 								 30,    10,      70,       110, 
 								 100, 20, 90, 60,  50, 120, 140, 130};	
		for(Integer value : array){
			heap.insert(value);
			heap.show();
			System.out.println("==========after insert " + value + "=========");
		}
//		System.out.println(heap.findMin());
	}
}
