package com.sqh.tool.algorithm.tree;

/**
 * 很老的平衡二叉树。<br/>
 * 一般的二叉查找树在删除时，如果只遵循以下的任意一种策略，都会在很多次操作之后，造成子树的不平衡，导致查询的效率大大下降<br/>
 * 1. 找到要删除的节点。<br/>
 * 2. 如果这个节点为一个树叶节点，更新父节点的指向该节点的指针为Null即可。<br/>
 * 3. 如果这个节点只有一个子节点，则更新父节点指向该节点的指针为子节点即可。<br/>
 * 4. 如果有两个子节点， 则找到 左子树最大值替换 或者 右子树的最小值， 替换到这个节点。<br/>
 * 5. 接下来， 使用方法3 删除左子树最大值， 或者 右子树最小值。<br/>
 * 
 * 避免上面的这种一般的操作造成的效率下降，一个是可以在删除时，第4步进行随机选择左、右子树<br/>
 * AVL(Adelson-Velskii 和 Landis)树则是给二叉查找树增加了平衡规则(每个节点的左子树和右子树的高度最多差1)<br/>
 * 在插入和删除操作时，通过重新平衡二叉树(旋转)，使得二叉查找树一直处于比较平衡的情况，保证查找操作的O(logN) 效率。<br/>
 * 
 * 在插入新的节点之后，只有那些从插入点到根节点的路径上的节点的平衡可能发生变化，当我们沿着这条路径上行到根并更新平衡信息时，我们可以<br/>
 * 找到一个节点，它的新平衡破坏了AVL条件，我们只需要调整这个节点的两个子树使得这个节点重新平衡。<br/>
 * 
 * 1. 左-左、右-右型 都是单旋。   左-右、 右-左 是双旋。<br/>
 * 2. 最终都会是插入节点的父节点  旋转到了 待平衡节点的位置，待平衡节点的子树旋转到新的位置达到平衡。
 * @author Administrator
 *
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>  AVLTree generateTree(T[] tArray){
		
		AVLTree tree = new AVLTree((Comparable) tArray[0]);
		for(int i = 1; i < tArray.length; i++){
			tree.insert((Comparable)tArray[i]);
		}
		return tree;
	}
	
	@SuppressWarnings("unchecked")
	public AVLTree(Comparable<T> t) {
		super((T) t);
	}

	@Override
	protected boolean insert(T t, BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		T ele = root2.getData();
		int result = t.compareTo(ele);
		boolean insertResult = false;
		if(result < 0){  //如果小于这个节点，插入节点左子树.
			BinaryTreeNode<T> leftChild = root2.getLeftChild();
			if(leftChild == null){
				root2.setLeftChild(new BinaryTreeNode<T>(t));
				insertResult = true;
			}else{
				insertResult = insert(t, leftChild, root2);
			}
			
			//这里做再平衡的操作
			if(calcHeightMinusOf(root2) == 2){
				if(t.compareTo(root2.getLeftChild().getData()) < 0){   //这属于左-左类型的。
					singleRotateWithLeft(root2, parentOfRoot2);
				}else{												   //这属于左-右类型的。
					doubleRotateWithLeft(root2, parentOfRoot2);
				}
			}
		}
		
		if(result > 0){  //如果大于这个节点，插入节点右子树.
			BinaryTreeNode<T> rightChild = root2.getRightChild();
			if(rightChild == null){
				root2.setRightChild(new BinaryTreeNode<T>(t));
				insertResult = true;
			}else{
				insertResult = insert(t, rightChild, root2);
			}
			
			//这里做再平衡的操作
			if(calcHeightMinusOf(root2) == 2){
				if(t.compareTo(root2.getLeftChild().getData()) < 0){   //这属于右-左类型的。
					doubleRotateWithRight(root2, parentOfRoot2);
				}else{                                                 //这属于右-右类型的。
					singleRotateWithRight(root2, parentOfRoot2);
				}
			}
		}
		
		//重新计算这个节点的高度.
		root2.setHeight(calcHeightByChildren(root2) + 1);
		
		if(result == 0){
			insertResult = false;
		}
		return insertResult;
	}
	
	private int calcHeightMinusOf(BinaryTreeNode<T> root2) {
		BinaryTreeNode<T> left = root2.getLeftChild();
		BinaryTreeNode<T> right = root2.getRightChild();
		if(left == null){
			if(right == null){
				return 0;
			}else{
				return right.getHeight();
			}
		}
		
		if(right == null){
			return left.getHeight();
		}
		int result = left.getHeight() - right.getHeight();
		if(result < 0){
			result = -result;
		}
		return result;
	}

	private int calcHeightByChildren(BinaryTreeNode<T> root2) {
		BinaryTreeNode<T> left = root2.getLeftChild();
		BinaryTreeNode<T> right = root2.getRightChild();
		if(left == null){
			if(right == null){
				return 0;
			}else{
				return right.getHeight();
			}
		}
		
		if(right == null){
			return left.getHeight();
		}
		
		return Math.max(left.getHeight(), right.getHeight());
	}

	/**
	 * 左 -右型 双旋<br/>
	 * @param root2
	 * @param parentOfRoot2
	 */
	private BinaryTreeNode<T> doubleRotateWithLeft(BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		root2.setLeftChild( singleRotateWithRight(root2.getLeftChild(), parentOfRoot2) );
		
		return singleRotateWithLeft(root2, parentOfRoot2);
	}

	/**
	 * 右-左型 双旋<br/>
	 * @param root2
	 * @param parentOfRoot2
	 */
	private BinaryTreeNode<T> doubleRotateWithRight(BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		root2.setRightChild( singleRotateWithLeft(root2.getRightChild(), parentOfRoot2) );
		
		return singleRotateWithRight(root2, parentOfRoot2);
		
	}

	/**
	 * 右-右型  单旋 <br/>
	 * 1. root2的右孩子变成新的根<br/>
	 * 2. root2变成新的根的左子树<br/>
	 * 3. root2的右孩子原来的左子树 成了 root2 的右子树。
	 * @param root2
	 * @Return BinaryTreeNode<T> 占据原root2位置的新节点
	 */
	private BinaryTreeNode<T> singleRotateWithRight(BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		BinaryTreeNode<T> rightChildOfRoot2 = root2.getRightChild();
		
		if(parentOfRoot2 != null){	  //root2 不是根节点。
			parentOfRoot2.setRightChild(rightChildOfRoot2);
		}else{                        //root2 是根节点。
			root = rightChildOfRoot2;
		}
		
		root2.setRightChild(rightChildOfRoot2.getLeftChild());
		rightChildOfRoot2.setLeftChild(root2);
		return rightChildOfRoot2;
	}

	/**
	 * 左-左型  单旋 <br/>
	 * 1. root2的左孩子变成新的根<br/>
	 * 2. root2的左孩子原来的右子树 成了 root2的左子树。<br/>
	 * 3. root2变成新的根的右子树<br/>
	 * @param root2
	 * @Return BinaryTreeNode<T> 占据原root2位置的新节点
	 */
	private BinaryTreeNode<T> singleRotateWithLeft(BinaryTreeNode<T> root2, BinaryTreeNode<T> parentOfRoot2) {
		BinaryTreeNode<T> leftOfRoot2 = root2.getLeftChild();
		
		if(parentOfRoot2 != null){    //root2 不是根节点。
			parentOfRoot2.setLeftChild(leftOfRoot2);
		}else{                        //root2 是根节点。
			root = leftOfRoot2;
		}
		root2.setLeftChild(leftOfRoot2.getRightChild());
		leftOfRoot2.setRightChild(root2);
		return leftOfRoot2;
	}

	@Override
	public boolean insert(T t) {
		boolean result= super.insert(t);
		if(!result){
			return false;
		}
		
		//这里做再平衡的操作
		return true;
	}

	@Override
	public boolean delete(T t) {
		boolean result= super.delete(t);
		if(!result){
			return false;
		}
		
		//这里做再平衡的操作
		return true;
	}
}
