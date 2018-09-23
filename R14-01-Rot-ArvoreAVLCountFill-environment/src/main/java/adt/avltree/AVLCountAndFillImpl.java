package adt.avltree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		T[] copy = Arrays.copyOf(array, array.length);
		Arrays.sort(copy);
		LinkedList<T> fila = new LinkedList<T>();
		for (int i = 0; i < copy.length; i++) {

		}
	}
	
	public void getHalf(T[] array, LinkedList<T> fila, int leftIndex, int rightIndex) {
		int tamanho = rightIndex - leftIndex + 1;
		if (tamanho > 1) {
			int half = (leftIndex+rightIndex)/2;
			if (tamanho % 2 == 0) {
				half += 1;
			}
			fila.add(array[half]);
			getHalf(array, fila, leftIndex, half-1);
			getHalf(array, fila, half+1, rightIndex);
		} else if (tamanho == 1) {
			fila.add(array[leftIndex]);
		}
	}
	
	// AUXILIARY
	@Override
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
			this.RRcounter++;
			break;
		case "LL":
			Util.rightRotation(node);
			this.LLcounter++;
			break;
		case "LR":
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
			this.LRcounter++;
			break;
		case "RL":
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
			this.RLcounter++;
			break;
		default:
			break;
		}
	}

}
