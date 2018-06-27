package adt.bst;

@SuppressWarnings("unchecked")
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = (BSTNode<T>) new BSTNode.Builder<T>().build();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root) - 1;
	}

	private int height(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			int leftHeight = height((BSTNode<T>) node.getLeft());
			int rightHeight = height((BSTNode<T>) node.getRight());

			if (leftHeight > rightHeight) {
				result += 1 + leftHeight;
			} else {
				result += 1 + rightHeight;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<T>();
		if (!node.isEmpty()) {
			int compare = element.compareTo(node.getData());
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
	public void insert(T element) {
		this.insert(element, this.root, null);
	}

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
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	protected BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				result = node;
			} else {
				result = maximum((BSTNode<T>) node.getRight());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.root);
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				result = node;
			} else {
				result = minimum((BSTNode<T>) node.getLeft());
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

				while (aux.getParent() != null && element.compareTo(aux.getData()) > 0) {
					aux = (BSTNode<T>) aux.getParent();
				}

				if (element.compareTo(aux.getData()) < 0) {
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

				while (aux.getParent() != null && element.compareTo(aux.getData()) < 0) {
					aux = (BSTNode<T>) aux.getParent();
				}

				if (element.compareTo(aux.getData()) > 0) {
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
					if (node.getData().compareTo(node.getParent().getLeft().getData()) == 0) {
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

	protected boolean isRoot(BSTNode<T> node) {
		boolean result = false;
		if (node.equals(this.root) && node.getParent() == null) {
			result = true;
		}
		return result;
	}

	@Override
	public T[] preOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		fillPreOrder(result, this.root, 0);
		return result;
	}

	private int fillPreOrder(T[] array, BSTNode<T> node, int position) {
		if (!node.isEmpty()) {
			array[position] = node.getData();
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

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		fillPostOrder(result, this.root, 0);
		return result;
	}

	private int fillPostOrder(T[] array, BSTNode<T> node, int position) {
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				position = fillPostOrder(array, (BSTNode<T>) node.getLeft(), position);
			}
			if (!node.getRight().isEmpty()) {
				position = fillPostOrder(array, (BSTNode<T>) node.getRight(), position);
			}
			array[position] = node.getData();
		} else {
			position--;
		}

		return position + 1;

	}

	@Override
	public T[] order() {
		T[] result = (T[]) new Comparable[this.size()];
		fillOrder(result, this.root, 0);
		return result;
	}

	private int fillOrder(T[] array, BSTNode<T> node, int position) {
		if (!node.isEmpty()) {

			if (!node.getLeft().isEmpty()) {
				position = fillOrder(array, (BSTNode<T>) node.getLeft(), position);
				position++;
			}

			array[position] = node.getData();

			if (!node.getRight().isEmpty()) {
				position++;
				position = fillOrder(array, (BSTNode<T>) node.getRight(), position);
			}
		}

		return position++;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
