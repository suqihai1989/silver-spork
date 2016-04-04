package com.sqh.tool.algorithm.tree;

/**
 * 树的结构的封装
 * @author Administrator
 * @param <T>
 */
public class TreeNode<T> {
	private T element;       //节点数据
	private TreeNode<T> firstChild;   //第一个孩子
	private TreeNode<T> nextSibling;  //下一个兄弟
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public TreeNode<T> getFirstChild() {
		return firstChild;
	}
	public void setFirstChild(TreeNode<T> firstChild) {
		this.firstChild = firstChild;
	}
	public TreeNode<T> getNextSibling() {
		return nextSibling;
	}
	public void setNextSibling(TreeNode<T> nextSibling) {
		this.nextSibling = nextSibling;
	}
}
