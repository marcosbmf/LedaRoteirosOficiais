package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			@SuppressWarnings("unchecked")
			BSTNode<T> copyNode = (BSTNode<T>) new BSTNode.Builder<T>().parent(node.getParent()).left(node.getLeft())
					.right(node.getRight()).data(node.getData()).build();
			//Pivot
			BSTNode<T> pivot = (BSTNode<T>) copyNode.getRight();
			
			//ROOT.OS = PIVOT.RS
			copyNode.setRight(pivot.getLeft());
			copyNode.getRight().setParent(copyNode);
			
			//PIVOT.RS = ROOT
			pivot.setLeft(copyNode);
			copyNode.setParent(pivot);

			//ROOT = PIVOT
			node.setData(pivot.getData());
			node.setLeft(pivot.getLeft());
			node.setRight(pivot.getRight());
			return pivot;
		}
		return null;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			//Copy of ROOT
			@SuppressWarnings("unchecked")
			BSTNode<T> copyNode = (BSTNode<T>) new BSTNode.Builder<T>().parent(node.getParent()).left(node.getLeft())
					.right(node.getRight()).data(node.getData()).build();
			
			//Pivot = ROOT.OS
			BSTNode<T> pivot = (BSTNode<T>) copyNode.getLeft();
			
			//ROOT.OS = PIVOT.RS
			copyNode.setLeft(pivot.getRight());
			copyNode.getLeft().setParent(copyNode);
			
			//PIVOT.RS = ROOT
			pivot.setRight(copyNode);
			copyNode.setParent(pivot);

			//ROOT = PIVOT
			node.setData(pivot.getData());
			node.setLeft(pivot.getLeft());
			node.setRight(pivot.getRight());
			return pivot;
		}
		return null;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
