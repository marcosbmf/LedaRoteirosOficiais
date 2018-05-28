package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> current = this.head;
		while (true) {
			if (current.isNIL()) {
				return size;
			} else {
				size += 1;
				current = current.getNext();
			}
		}
			
		
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> current = this.head;
		while (true) {
			if (current.isNIL()) {
				return null;
			} else if (current.getData().equals(element)) {
					return element;
			} else {
				current = current.getNext();
			}
		}
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> current = this.head;
		while (!current.isNIL()) {
			current = current.getNext();
		}
		current.setData(element);
		current.setNext(new SingleLinkedListNode<T>());
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> current = this.head;
		if (!current.isNIL()) {
			//Caminha na lista até que o próximo elemento seja NIL ou tenha o elemento procurado.
			while (!current.getNext().isNIL() && !current.getNext().getData().equals(element)) {
				current = current.getNext();
			}
			//Verifica se o próximo possui o elemento procurado.
			if (current.getNext().getData().equals(element)) {
				//Seta o próximo, pulando o elemento eliminado.
				current.setNext(current.getNext().getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		int index = 0;
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> current = this.head;
		for (int i = 0; i < this.size(); i++) {
			array[index] = current.getData();
			index ++;
			current = current.getNext();
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
