package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */

@SuppressWarnings("unchecked")
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	private int compare(T o1, T o2) {
		if (comparator == null) {
			return o1.compareTo(o2);
		} else {
			return comparator.compare(o1, o2);
		}
	}

	@Override
	public T[] sort(T[] array) {
		T[] sorted = (T[]) new Comparable[this.size()];
		SortComparatorBST<T> sort = new SortComparatorBSTImpl<T>(this.comparator);
		for (int i = 0; i < array.length; i++) {
			sort.insert(array[i]);
		}
		for (int i = array.length; i >= 0; i--) {
			BSTNode<T> maximum = sort.maximum();
			sorted[i] = maximum.getData();
			sort.remove(maximum.getData());
		}
		return sorted;
	}
	
	@Override
	public void insert(T element) {
		this.insert(element, this.root, null);
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> previous) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
			node.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
		} else {
			if (this.compare(element, node.getData()) > 0) {
				this.insert(element, (BSTNode<T>) node.getRight(), node);
			} else if (this.compare(element, node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft(), node);
			}
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<T>();
		if (!node.isEmpty()) {
			int compare = this.compare(element, node.getData());
			if (compare == 0) {
				result = node;
			} else if (compare > 0 && node.getRight() != null) {
				result = search(element, (BSTNode<T>) node.getRight());
			} else if (node.getLeft() != null) {
				result = search(element, (BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = this.search(element);

		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				result = this.minimum((BSTNode<T>) node.getRight());

			} else if (node.getParent() != null) {

				BSTNode<T> aux = (BSTNode<T>) node.getParent();

				while (aux.getParent() != null && this.compare(element, aux.getData()) > 0) {
					aux = (BSTNode<T>) aux.getParent();
				}

				if (this.compare(element, aux.getData()) < 0) {
					result = aux;
				}

			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = this.search(element);

		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				result = this.maximum((BSTNode<T>) node.getLeft());
			} else if (node.getParent() != null) {

				BSTNode<T> aux = (BSTNode<T>) node.getParent();

				while (aux.getParent() != null && this.compare(element, aux.getData()) < 0) {
					aux = (BSTNode<T>) aux.getParent();
				}

				if (this.compare(element, aux.getData()) > 0) {
					result = aux;
				}
			}
		}

		return result;
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		this.remove(node);
	}
	
	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if (!isRoot(node)) {
					if (this.compare(node.getData(), node.getParent().getLeft().getData()) == 0) {
						if (node.getLeft().isEmpty()) {
							node.getRight().setParent(node.getParent());
							node.getParent().setLeft(node.getRight());
						} else {
							node.getLeft().setParent(node.getParent());
							node.getParent().setLeft(node.getLeft());
						}
					} else {
						if (node.getLeft().isEmpty()) {
							node.getRight().setParent(node.getParent());
							node.getParent().setRight(node.getRight());
						} else {
							node.getLeft().setParent(node.getParent());
							node.getParent().setRight(node.getLeft());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
						this.root.setParent(null);
					} else {
						this.root = (BSTNode<T>) node.getLeft();
						this.root.setParent(null);
					}
				}
			} else {
				BSTNode<T> suc = this.sucessor(node.getData());
				if (suc != null) {
					node.setData(suc.getData());
					this.remove(suc);
				}
			}
		}
	}
	
	@Override
	public T[] reverseOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		fillReverseOrder(result, this.root, 0);
		return result;
	}
	
	
	private int fillReverseOrder(T[] array, BSTNode<T> node, int position) {
		if (!node.isEmpty()) {

			if (!node.getRight().isEmpty()) {
				position = fillReverseOrder(array, (BSTNode<T>) node.getRight(), position);
				position++;
			}

			array[position] = node.getData();
			
			if (!node.getLeft().isEmpty()) {
				position++;
				position = fillReverseOrder(array, (BSTNode<T>) node.getLeft(), position);
			}

			
		}

		return position++;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
