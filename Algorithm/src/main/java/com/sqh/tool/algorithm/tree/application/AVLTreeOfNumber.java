package com.sqh.tool.algorithm.tree.application;

import com.sqh.tool.algorithm.tree.AVLTree;

public class AVLTreeOfNumber extends AVLTree<Integer> {

	public AVLTreeOfNumber(Comparable<Integer> t) {
		super(t);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args){
//		Integer[] ints = new Integer[]{5, 3, 6, 4, 2};
//		AVLTree<Integer> tree = AVLTree.generateTree(ints);
//		System.out.println("pre ->" + tree.preOrder());
//		System.out.println("mid ->" + tree.midOrder());
//		System.out.println("post->" + tree.postOrder());
//		tree.insert(1);
//		System.out.println("pre ->" + tree.preOrder());
//		System.out.println("mid ->" + tree.midOrder());
//		System.out.println("post->" + tree.postOrder());
		
		//双旋测试用例。
/**
 *     6                     6                                    6                   5
 *   /  \                   / \                                  / \                 / \
 *  3    7   插入4之后  ->     3   7  -> 6不平衡，属于左-右型(6->3->5)  -> 5  7  -> 再对左孩子   -> 3   6
 * / \                    / \		  先对左孩子进行一次左旋                                      /        进行一次右旋        / \   \
 * 2  5                  2  5                                  3                   2  4   7
 *                         /                                  / \
 *                        4                                  2   4
 * 相当于，我们要把5挪到6，在这种类型中(左-右型)，要左旋再右旋 共两次旋转。
 */
		Integer[] ints = new Integer[]{6, 3, 7, 5, 2};
		AVLTree<Integer> tree = AVLTree.generateTree(ints);
		System.out.println("pre ->" + tree.preOrder());
		System.out.println("mid ->" + tree.midOrder());
		System.out.println("post->" + tree.postOrder());
		assert(tree.midOrder().equals("2 3 5 6 7 "));
		tree.insert(4);
		System.out.println("pre ->" + tree.preOrder());
		System.out.println("mid ->" + tree.midOrder());
		System.out.println("post->" + tree.postOrder());
		assert(tree.midOrder().equals("2 3 4 5 6 7 "));
		
	}
}
