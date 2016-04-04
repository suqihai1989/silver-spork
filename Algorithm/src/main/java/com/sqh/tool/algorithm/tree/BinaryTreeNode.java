package com.sqh.tool.algorithm.tree;

/**
 * 二叉树节点的结构
 * @author Administrator
 * @param <T>
 */
public class BinaryTreeNode<T> {
	private T data;
	private int height;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	public BinaryTreeNode(T t){
		data = t;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
