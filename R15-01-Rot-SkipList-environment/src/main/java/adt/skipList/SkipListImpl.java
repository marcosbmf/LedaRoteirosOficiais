package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		if (height > this.maxHeight) {
			throw new IllegalArgumentException();
		} else {
			SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
			SkipListNode<T>[] path = this.pathToPotentialPrevious(key);
			if (path[0].forward[0].getKey() == key) {
				path[0].forward[0].setValue(newValue);
			} else {
				this.updateNodesInsert(newNode, path);
			}
		}
	}

	private void updateNodesInsert(SkipListNode<T> newNode, SkipListNode<T>[] path) {
		for (int level = 0; level < newNode.forward.length; level++) {
			newNode.forward[level] = path[level].forward[level];
			path[level].forward[level] = newNode;
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] path = this.pathToPotentialPrevious(key);
		if (path[0].forward[0].getKey() == key) {
			SkipListNode<T> removed = path[0].forward[0];
			updateNodesRemove(removed, path);
		}
	}

	private void updateNodesRemove(SkipListNode<T> removed, SkipListNode<T>[] path) {
		for (int level = 0; level < removed.forward.length; level ++) {
			path[level].forward[level] = removed.getForward(level);
		}
	}

	@Override
	public int height() {
		SkipListNode<T> node = root.forward[root.forward.length - 1];
		int height = 0;
		while (node.getKey() != Integer.MAX_VALUE) {
			if (node.forward.length > height) {
				height = node.forward.length;
			}
			node = node.forward[node.forward.length - 1];
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> potentialPrevious = this.pathToPotentialPrevious(key)[0];
		SkipListNode<T> searchResult;
		if (potentialPrevious.forward[0].getKey() == key) {
			searchResult = potentialPrevious.forward[0];
		} else {
			searchResult = null;
		}
		
		return searchResult;
	}
	
	private SkipListNode<T>[] pathToPotentialPrevious(int key){
		SkipListNode<T> node = root;
		int level = this.maxHeight;
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] path = (SkipListNode<T>[]) new SkipListNode[level];
		while (level != 0) {
			while (node.forward[level - 1].getKey() < key) {
				node = node.forward[level - 1];
			}
			level -= 1;
			path[level] = node;
		}
		return path;
	}

	@Override
	public int size() {
		SkipListNode<T> node = root.forward[0];
		int count = 0;
		while (node.getKey() != Integer.MAX_VALUE) {
			count += 1;
			node = node.forward[0];
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size();
		SkipListNode<T>[] array = (SkipListNode<T>[]) new SkipListNode[size+2];
		SkipListNode<T> node = this.root;
		array[0] = this.root;
		array[size+1] = this.NIL;
		for (int i = 1; i < size + 1; i++) {
			node = node.forward[0];
			array[i] = node;
		}
		return array;
	}
}
