package com.sqh.tool.algorithm.tree;


/**
 * 二叉查找树
 * @author Administrator
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T> > implements Tree<T> {

	protected BinaryTreeNode<T> root;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>  BinarySearchTree generateTree(T[] tArray){
		
		BinarySearchTree tree = new BinarySearchTree((Comparable) tArray[0]);
		for(int i = 1; i < tArray.length; i++){
			tree.insert((Comparable)tArray[i]);
		}
		return tree;
	}
	
	public BinarySearchTree(T t){
		root = new BinaryTreeNode<T>(t);
	}
	
	@Override
	public T find(T t) {
		BinaryTreeNode<T> position = findPos(t);
		if(position != null){
			return position.getData();
		}
		return null;
	}
	
	public BinaryTreeNode<T> findPos(T t) {
		if(t == null){
			return null;
		}
		
		return find(t, root);
	}

	private BinaryTreeNode<T> find(T t, BinaryTreeNode<T> root2) {
		T ele = root2.getData();
		int result = t.compareTo(ele);
		if(result < 0){  //如果小于这个节点，则去节点左子树查找.
			BinaryTreeNode<T> leftChild = root2.getLeftChild();
			if(leftChild == null){
				return null;
			}
			return find(t, leftChild);
		}
		
		if(result > 0){  //如果大于这个节点，则去节点右子树查找.
			BinaryTreeNode<T> rightChild = root2.getRightChild();
			if(rightChild == null){
				return null;
			}
			return find(t, rightChild);
		}
		
		return root2;
	}
	
	@Override
	public int heigh() {
		return 0;
	}

	@Override
	public boolean insert(T t) {
		return insert(t, root, null);
	}

	protected boolean insert(T t, BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		T ele = root2.getData();
		int result = t.compareTo(ele);
		if(result < 0){  //如果小于这个节点，插入节点左子树.
			BinaryTreeNode<T> leftChild = root2.getLeftChild();
			if(leftChild == null){
				root2.setLeftChild(new BinaryTreeNode<T>(t));
				return true;
			}
			return insert(t, leftChild, root2);
		}
		
		if(result > 0){  //如果大于这个节点，插入节点右子树.
			BinaryTreeNode<T> rightChild = root2.getRightChild();
			if(rightChild == null){
				root2.setRightChild(new BinaryTreeNode<T>(t));
				return true;
			}
			return insert(t, rightChild, root2);
		}
		
		return false;
	}

	@Override
	public boolean delete(T t) {
		NodeAndParent nodeAndParent = findNodeAndParent(t, root, null, null);
		
		if(nodeAndParent == null){ //没有找到该节点.
			return false;
		}
		
		BinaryTreeNode<T> node2Del = nodeAndParent.node;
		BinaryTreeNode<T> parent = nodeAndParent.parent;
		LeftOrRight lOrRight = nodeAndParent.direct;
		
		BinaryTreeNode<T> leftChild = node2Del.getLeftChild();
		BinaryTreeNode<T> rightChild = node2Del.getRightChild();
		if(leftChild == null){         
			if(rightChild == null){		//要删除的节点没有子树的情况, 则置空父节点的相应子树。
				replaceDeletedNode(parent, null, lOrRight);
			}else{   					//要删除的节点只有右子树, 则使用右子树来替换要删除的节点。
				replaceDeletedNode(parent, rightChild, lOrRight);
			}
		}else if(rightChild == null){   //要删除的节点只有左子树, 则使用左子树来替换要删除的节点。
			replaceDeletedNode(parent, leftChild, lOrRight);
		}else{    						
			//要删除的节点有左右子树的情况
			//1.先找到右子树上的最小值与待删的节点替换
			//2.再删除最小值节点
			NodeAndParent minOfRightTree = findMin(rightChild, node2Del, LeftOrRight.RIGHT);
			BinaryTreeNode<T> minNode = minOfRightTree.node;
			node2Del.setData(minNode.getData());
			
			//删除掉最小值节点, 这个节点的左子树必然是null.
			BinaryTreeNode<T> rightOfMinNode = minNode.getRightChild();
			if(rightOfMinNode == null){
				replaceDeletedNode(minOfRightTree.parent, null, minOfRightTree.direct);
			}else{   					
				replaceDeletedNode(minOfRightTree.parent, rightOfMinNode, minOfRightTree.direct);
			}
		}
		
		return true;
	}

	private void replaceDeletedNode(BinaryTreeNode<T> parent, BinaryTreeNode<T> newNode, LeftOrRight lOrRight){
		if(lOrRight == LeftOrRight.LEFT){
			parent.setLeftChild(newNode);
		}else{
			parent.setRightChild(newNode);
		}
	}
	
	@Override
	public String preOrder() {
		StringBuilder sb = new StringBuilder();
		preOrder(sb, root);
		return sb.toString();
	}

	private void preOrder(StringBuilder sb, BinaryTreeNode<T> root2) {
		//先序(父先)遍历，先打印父节点，再打印左右子树。
		if(root2 == null){
			return ;
		}
		sb.append(root2.getData().toString()).append(' ');
		preOrder(sb, root2.getLeftChild());
		preOrder(sb, root2.getRightChild());
	}

	@Override
	public String midOrder() {
		StringBuilder sb = new StringBuilder();
		midOrder(sb, root);
		return sb.toString();
	}
	
	private void midOrder(StringBuilder sb, BinaryTreeNode<T> root2) {
		//中序(父中)遍历，先打印左子树，再打印父节点, 最后打右子树
		if(root2 == null){
			return ;
		}
		midOrder(sb, root2.getLeftChild());
		sb.append(root2.getData().toString()).append(' ');
		midOrder(sb, root2.getRightChild());
	}

	@Override
	public String postOrder() {
		StringBuilder sb = new StringBuilder();
		postOrder(sb, root);
		return sb.toString();
	}
	
	private void postOrder(StringBuilder sb, BinaryTreeNode<T> root2) {
		//后序(父后)遍历，先打印左右子树，再打印父节点。
		if(root2 == null){
			return ;
		}
		postOrder(sb, root2.getLeftChild());
		postOrder(sb, root2.getRightChild());
		sb.append(root2.getData().toString()).append(' ');
	}
	
	private NodeAndParent findNodeAndParent(T t, BinaryTreeNode<T> node, BinaryTreeNode<T> parent, LeftOrRight lOrRight){
		T ele = node.getData();
		int result = t.compareTo(ele);
		if(result < 0){  //如果小于这个节点，则去节点左子树查找.
			BinaryTreeNode<T> leftChild = node.getLeftChild();
			if(leftChild == null){
				return null;
			}
			return findNodeAndParent(t, leftChild, node, LeftOrRight.LEFT);
		}
		
		if(result > 0){  //如果大于这个节点，则去节点右子树查找.
			BinaryTreeNode<T> rightChild = node.getRightChild();
			if(rightChild == null){
				return null;
			}
			return findNodeAndParent(t, rightChild, node, LeftOrRight.RIGHT);
		}
		
		return new NodeAndParent(node, parent, lOrRight);
	}
	
	public NodeAndParent findMin(BinaryTreeNode<T> node, BinaryTreeNode<T> parent, LeftOrRight lOrRight){
		BinaryTreeNode<T> left = node.getLeftChild();
		if(left == null){   //如果左子树已经是空，则这个节点已经是最小值。
			return new NodeAndParent(node, parent, lOrRight);
		}else{
			return findMin(left, node, LeftOrRight.LEFT);
		}
	}
	
	public void show(){
		
	}
	
	enum LeftOrRight{
		LEFT,
		RIGHT
	}
	
	class NodeAndParent{
		BinaryTreeNode<T> node;
		BinaryTreeNode<T> parent;
		LeftOrRight direct;
		
		public NodeAndParent(BinaryTreeNode<T> node, BinaryTreeNode<T> parent, LeftOrRight lOrRight) {
			this.node = node;
			this.parent = parent;
			this.direct = lOrRight;
		}
	}
}
