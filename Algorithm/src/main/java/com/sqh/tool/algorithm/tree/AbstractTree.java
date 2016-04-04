package com.sqh.tool.algorithm.tree;

public abstract class AbstractTree<T extends Comparable<T>> implements Tree<T>{
	private TreeNode<T> root;
	
	public AbstractTree(){
		root = new TreeNode<T>();
	}
	
	@Override
	public T find(T t) {
		if(t == null){
			return null;
		}
		
		return find(t, root);
	}

	private T find(T t, TreeNode<T> root2) {
		T ele = root2.getElement();
		if(t.equals(ele)){
			return ele;
		}
		
		T eleFromChild = find(t, root2.getFirstChild());
		if (eleFromChild != null) {
			return eleFromChild;
		}

		T eleFromSibling = find(t, root2.getNextSibling());
		return eleFromSibling;
	}

	@Override
	public int heigh() {
		return 0;
	}

	@Override
	public boolean insert(T t) {
		return false;
	}

	@Override
	public boolean delete(T t) {
		return false;
	}

	@Override
	public String preOrder() {
		return null;
	}

	@Override
	public String midOrder() {
		return null;
	}

	@Override
	public String postOrder() {
		return null;
	}
}
