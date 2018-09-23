package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return this.blackHeight(this.root);
	}

	private int blackHeight(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			int leftBlackHeight = blackHeight((BSTNode<T>) node.getLeft());
			int rightBlackHeight = blackHeight((BSTNode<T>) node.getRight());

			if (((RBNode<T>) node).getColour() == Colour.BLACK) {
				result = 1;
			}

			if (leftBlackHeight > rightBlackHeight) {
				result += leftBlackHeight;
			} else {
				result += rightBlackHeight;
			}

		}
		return result;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) this.root);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean ans = true;

		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				ans = isBlack((RBNode<T>) node.getLeft()) && isBlack((RBNode<T>) node.getRight());
			}
			
			ans = ans && verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		
		return ans;
	}

	private boolean isBlack(RBNode<T> node) {
		return node.getColour() == Colour.BLACK;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		boolean ans = false;
		if (!this.root.isEmpty()) {
			if (this.blackHeight((BSTNode<T>) root.getLeft()) == this.blackHeight((BSTNode<T>) root.getRight())) {
				return true;
			}
		} else {
			ans = true;
		}
		
		return ans;
	}

	@Override
	public void insert(T element) {
		this.insert(element, (RBNode<T>) this.root, null);
	}

	private void insert(T element, RBNode<T> node, BSTNode<T> previous) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setColour(Colour.RED);
			RBNode<T> NIL = new RBNode<T>();
			NIL.setParent(node);
			node.setLeft(NIL);
			NIL = new RBNode<T>();
			NIL.setParent(node);
			node.setRight(NIL);
			this.fixUpCase1(node);
		} else {
			if (element.compareTo(node.getData()) > 0) {
				this.insert(element, (RBNode<T>) node.getRight(), node);
			} else if (element.compareTo(node.getData()) < 0) {
				this.insert(element, (RBNode<T>) node.getLeft(), node);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] result = (RBNode<T>[]) new RBNode[this.size()];
		fillPreOrder(result, this.root, 0);
		return result;
	}

	private int fillPreOrder(RBNode<T>[] array, BSTNode<T> node, int position) {
		if (!node.isEmpty()) {
			array[position] = (RBNode<T>) node;
			if (!node.getLeft().isEmpty()) {
				position = fillPreOrder(array, (BSTNode<T>) node.getLeft(), position + 1);
			}
			if (!node.getRight().isEmpty()) {
				position = fillPreOrder(array, (BSTNode<T>) node.getRight(), position + 1);
			}
		} else {
			position--;
		}

		return position;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node == root) {
			node.setColour(Colour.BLACK);
		} else {
			this.fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (!this.isBlack((RBNode<T>) node.getParent())) {
			this.fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandpa = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = this.getUncle(node);
		if (!this.isBlack(uncle)) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandpa.setColour(Colour.RED);
			this.fixUpCase1(grandpa);
		} else {
			this.fixUpCase4(node);
		}
		
	}
	
	private RBNode<T> getUncle(RBNode<T> node){
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (this.isLeftChild(parent)) {
			return (RBNode<T>) parent.getParent().getRight();
		} else {
			return (RBNode<T>) parent.getParent().getLeft();
		}
	}
	
	private boolean isRightChild(RBNode<T> node) {
		return node.getParent().getRight().equals(node);
	}
	
	private boolean isLeftChild(RBNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		if (this.isRightChild(node) && this.isLeftChild((RBNode<T>)node.getParent())){
			Util.leftRotation((BSTNode<T>) node.getParent());
			next =  (RBNode<T>) node.getLeft();
		} else if (this.isLeftChild(node) && this.isRightChild((RBNode<T>)node.getParent())) {
			Util.rightRotation((RBNode<T>)node.getParent());
			next = (RBNode<T>) node.getRight();
		}
		
		this.fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>)node.getParent();
		RBNode<T> grandpa = (RBNode<T>)parent.getParent();
		
		parent.setColour(Colour.BLACK);
		grandpa.setColour(Colour.RED);
		
		if (this.isLeftChild(node)) {
			Util.rightRotation(grandpa);
		} else {
			Util.leftRotation(grandpa);
		}
	}
}
