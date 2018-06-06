package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if (this.isEmpty()) {
			return null;
		} else {
			if (this.data.equals(element)) {
				return element;
			} else {
				return this.next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty() && element != null) {
			if (data.equals(element)) {
				this.setData(this.next.getData());
				this.setNext(this.next.getNext());
			} else {
				this.next.remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		fillArray(array, 0);
		return array;
	}
	
	private T[] fillArray(T[] array, int index) {
		if (this.data == null) {
			return array;
		} else {
			array[index] = this.data;
			index++;
			return next.fillArray(array, index);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
