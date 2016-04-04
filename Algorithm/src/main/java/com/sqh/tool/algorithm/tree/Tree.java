package com.sqh.tool.algorithm.tree;

/**
 * 树的抽象
 * @author Administrator
 */
public interface Tree<T extends Comparable<T>> {
	/**
	 * 从树中查找到节点
	 * @param t
	 * @return
	 */
	public T find(T t);
	
	/**
	 * 计算树的深度
	 * @return
	 */
	public int heigh();
	
	/**
	 * 插入一个节点
	 * @param t
	 * @return
	 */
	public boolean insert(T t);
	
	/**
	 * 删除一个节点
	 * @param t
	 * @return
	 */
	public boolean delete(T t);
	
	/**
	 * 先序遍历结果
	 * @return
	 */
	public String preOrder();
	
	/**
	 * 中序遍历结果
	 * @return
	 */
	public String midOrder();
	
	/**
	 * 后序遍历结果
	 * @return
	 */
	public String postOrder();

}
