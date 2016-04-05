package com.sqh.tool.algorithm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个堆数据结构将由一个数组，一个代表最大值的整数以及当前的堆大小组成。
 * @author Administrator
 * @param <T>
 */
public class DefaultMinHeap<T extends Comparable<T>> implements Heap<T>{
	private int capacity;
	private int size;
	private List<T> tList;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static<T> DefaultMinHeap build(T[] list, int capacity){
		DefaultMinHeap ret = new DefaultMinHeap(capacity);
		for(T t : list){
			ret.tList.add(t);
		}
		
		ret.size = list.length - 1;
		for(int i = (ret.size-1)/2; i >= 0; i--){
			percolateDown(i, ret.tList);
		}
		return ret;
	}
	
	/**
	 * 将i节点与儿子节点比较，最小值与i节点交换。
	 * @param i
	 */
	private static<T extends Comparable<T>> void percolateDown(int i, List<T> list) {
		int left = i*2 + 1;
		int right = (i+1)*2;
		T t = list.get(i);
		T leftChild = list.get(left);
		T rightChild = list.get(right);
		
		int i2l = t.compareTo(leftChild);
		int i2r = t.compareTo(rightChild);
		int l2r = leftChild.compareTo(rightChild);
		
		if(i2l < 0){
			if(i2r < 0){ //i最小。
				//do nothing.
			}else{       //r最小。
				list.set(i, rightChild);
				list.set(right, t);
			}
		}else{
			if(l2r < 0){ //l最小。
				list.set(i, leftChild);
				list.set(left, t);
			}else{       //r最小。
				list.set(i, rightChild);
				list.set(right, t);
			}
		}
	}

	public DefaultMinHeap(int capacity){
		this.capacity = capacity;
		this.size = 0;
		this.tList = new ArrayList<>(capacity);
	}
	
	@Override
	public void insert(T t) {
		int insertIndex = size;
		if(insertIndex == 0){   //如果已经是根，则直接把新插入的值放到根上，退出循环。
			tList.add(t);
			size++;
			return ;
		}
		
		int parentIndex = (size+1)/2 - 1;
		parentIndex = parentIndex < 0? 0: parentIndex;
		while(insertIndex >= 0){
			if(insertIndex == 0){   //如果已经是根，则直接把新插入的值放到根上，退出循环。
				tList.set(0, t);
				size++;
				break;
			}
			
			T parentNode = tList.get(parentIndex);
			if(parentNode.compareTo(t) < 0){   //如果这个节点可以满足堆序，则退出循环。
				if(insertIndex == size){
					tList.add(t);
				}else{
					tList.set(insertIndex, t);
				}
				size++;
				break;
			}
			
			//将父节点挪到空穴位置
			if(insertIndex == size){
				tList.add(parentNode);
			}else{
				tList.set(insertIndex, parentNode);
			}
			insertIndex = parentIndex;
			parentIndex = (insertIndex+1)/2 - 1;
		}
	}

	@Override
	public T findMin() {
		if(size == 0){
			return null;
		}
		return tList.get(0);
	}

	/**
	 * 当删除一个最小元时，在根节点产生一个空穴。由于堆入了一个元素，则堆的最后一个元素X必须移动到该堆的某个地方。<br/>
	 * 如果X可以被放到空穴中，那么DeleteMin完成。不过这一般不太可能，将空穴的两个儿子中较小者移入空穴，<br/>
	 * 空穴下推一层，重复该步骤直到X可以被放入空穴中，这种策略为下滤(percolate down)。
	 */
	@Override
	public T deleteMin() {
		T ret = tList.get(0);
		T lastOne = tList.get(size - 1);
		
		if(ret instanceof Integer){
			Integer aInteger = (Integer)ret;
			if(aInteger == 5){
				System.out.println("");
			}
		}
		
		int curBlank = 0;  //当前空穴位置
		int minChildIndex = getMinChild(curBlank);
		while(minChildIndex > 0 && minChildIndex < size - 1){
			T minChild = tList.get(minChildIndex);
			int com = lastOne.compareTo(minChild);
			
			//如果最后一个放在这个位置满足堆序，就放在这。
			if(com <= 0){
				tList.set(curBlank, lastOne);
				size--;
				return ret;
			}
			
			//将较小的儿子放在当前空穴，空穴移动到较小的儿子位置。
			tList.set(curBlank, minChild);
			curBlank = minChildIndex;
			minChildIndex = getMinChild(curBlank);
		}
		
//		System.out.println("adfadfadf");
		tList.set(curBlank, lastOne);
		size--;
		return ret;
	}

	/**
	 * 获取两个孩子中，小的那一个。
	 * @param curBlank
	 * @return
	 */
	private int getMinChild(int curBlank) {
		int left = curBlank*2+1;
		int right = (curBlank+1)*2;
//		System.out.println("getMinChild, curBlank=" + curBlank + ", left=" 
//				+ left + ", right=" + right + ", size=" + size);
		if(left > size - 1){
			if(right > size - 1){
				return -1;
			}
			return right;
		}
		if(right > size - 1){
			return left;
		}
		
		int com = tList.get(left).compareTo(tList.get(right));
		if(com <= 0){
			return left;
		}
		return right;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size >= capacity - 1;
	}

	@Override
	public void show() {
		int sum = 1;
		int height = 0;
		for(; sum < size; height++){
			sum = sum*2;
		}
		
		StringBuilder sb = new StringBuilder();
		int lineMax = 1;
		int blank = height*2;
		for(int i = 0; i < height - 1; i++){
			blank *= 2;
		}
		for(int i = 0; i <= height; i++){
			int j = lineMax - 1;
			if(lineMax == 1){
				j = 0;
			}

			lineMax = 2*lineMax;
			
			for(; j < (lineMax - 1) && j < size; j++){
				for(int k = 0; k < blank; k++){
					sb.append(' ');
				}
				sb.append(tList.get(j));
			}
			sb.append("\n");
			blank = blank/(lineMax+1)*(lineMax/2 + 1);
		}
		System.out.println(sb.toString());
	}
}
