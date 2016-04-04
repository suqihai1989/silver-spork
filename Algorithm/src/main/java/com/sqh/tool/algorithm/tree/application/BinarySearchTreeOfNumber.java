package com.sqh.tool.algorithm.tree.application;

import com.sqh.tool.algorithm.tree.BinarySearchTree;

/**
 * 数据为数字的二插查找树。
 * @author Administrator
 */
public class BinarySearchTreeOfNumber extends BinarySearchTree<Integer> {
	
	public BinarySearchTreeOfNumber(Integer t) {
		super(t);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Integer[] ints = new Integer[]{4, 2, 1, 3, 5, 6};
		BinarySearchTree<Integer> tree = BinarySearchTreeOfNumber.generateTree(ints);
		System.out.println("pre ->" + tree.preOrder());
		System.out.println("mid ->" + tree.midOrder());
		System.out.println("post->" + tree.postOrder());
		tree.delete(2);
		System.out.println("pre ->" + tree.preOrder());
		System.out.println("mid ->" + tree.midOrder());
		System.out.println("post->" + tree.postOrder());
	}
}
