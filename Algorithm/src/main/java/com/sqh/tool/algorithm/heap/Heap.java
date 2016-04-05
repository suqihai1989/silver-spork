package com.sqh.tool.algorithm.heap;

/**
 * 堆结构接口定义
 * @author Administrator
 * @param <T>
 */
public interface Heap<T extends Comparable<T>> {
	/**
	 * 插入一个元素
	 * @param t
	 */
	public void insert(T t);
	
	public T findMin();
	
	public T deleteMin();
	
	public boolean isEmpty();
	
	public boolean isFull();

	public void show();
}
