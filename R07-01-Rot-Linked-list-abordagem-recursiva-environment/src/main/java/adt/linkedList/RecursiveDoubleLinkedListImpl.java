package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.insertFirst(element);
			} else if (this.next.isEmpty()) {
				this.next.data = element;
				if (this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
					this.next.next = new RecursiveDoubleLinkedListImpl<T>(null, null, (RecursiveDoubleLinkedListImpl<T>) this.next);
				}
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<T>(null, null, this);
				this.previous = new RecursiveDoubleLinkedListImpl<T>(null, this, null);
			} else {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>(this.data, this.next, this);
				if (aux.next instanceof RecursiveDoubleLinkedListImpl<?>) {
					((RecursiveDoubleLinkedListImpl<T>) aux.next).previous = aux;
				}
				this.data = element;
				this.next = aux;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty() && element != null) {
			if (element.equals(this.data)) {
				if (this.previous.isEmpty()) {
					this.removeFirst();
				} else {
					if (this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
						((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this.previous;
					}
					this.previous.next = this.next;
				}
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
			} else {
				this.data = this.next.data;
				this.next = this.next.next;
				if (this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				if (this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
					((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
