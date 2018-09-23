package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
		this.insert(element, this.root, null);
	}

	@SuppressWarnings("unchecked")
	private void insert(T element, BSTNode<T> node, BSTNode<T> previous) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
			node.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) > 0) {
				this.insert(element, (BSTNode<T>) node.getRight(), node);
			} else if (element.compareTo(node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft(), node);
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = this.search(element);
			this.remove(node);
			rebalanceUp(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (!node.getRight().isEmpty()) {
				BSTNode<T> sucessor = sucessor(node.getData());
				T aux = node.getData();
				node.setData(sucessor.getData());
				sucessor.setData(aux);
				this.remove(sucessor);
			} else if (!node.getLeft().isEmpty()) {
				BSTNode<T> predecessor = predecessor(node.getData());
				T aux = node.getData();
				node.setData(predecessor.getData());
				predecessor.setData(aux);
				this.remove(predecessor);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int left = this.height((BSTNode<T>) node.getLeft());
			int right = this.height((BSTNode<T>) node.getRight());
			return left - right;
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		String rotation = "";
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (balance > 1) {
				rotation += "L";
				balance = calculateBalance((BSTNode<T>) node.getLeft());
			} else if (balance < -1) {
				rotation += "R";
				balance = calculateBalance((BSTNode<T>) node.getRight());
			}
			
			if (balance > 0) {
				rotation += "L";
				balance = calculateBalance((BSTNode<T>) node.getLeft());
			} else if (balance < 0) {
				rotation += "R";
				balance = calculateBalance((BSTNode<T>) node.getRight());
			}
			
		}

		switch (rotation) {
		case "RR":
			Util.leftRotation(node);
			break;
		case "LL":
			Util.rightRotation(node);
			break;
		case "LR":
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
			break;
		case "RL":
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
			break;
		default:
			break;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
