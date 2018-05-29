package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		super();
		this.last = new DoubleLinkedListNode<T>();
		this.head = this.last;
	}
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
		
		if (this.isEmpty()) {
			
			newHead.setData(element);
			newHead.setNext(new DoubleLinkedListNode<T>());
			newHead.setPrevious(new DoubleLinkedListNode<T>());
			
			this.setHead(newHead);
			this.setLast(newHead);
			
		} else {
			
			DoubleLinkedListNode<T> lastHead = new DoubleLinkedListNode<T>();
			
			//Cópia de head para nova estrutura que aceita previous.
			lastHead.setNext(this.head.getNext());
			lastHead.setData(this.head.getData());
			lastHead.setPrevious(newHead);
			
			//Nova Head
			newHead.setData(element);
			newHead.setNext(lastHead);
			newHead.setPrevious(new DoubleLinkedListNode<T>());
			
			this.setHead(newHead);
		}
	}
	
	@Override
	public void insert(T element) {
		
		if (this.isEmpty()) {
			this.insertFirst(element);
		} else {
			DoubleLinkedListNode<T> current = this.last;
			DoubleLinkedListNode<T> last = (DoubleLinkedListNode<T>) current.getNext();
			
			last.setData(element);
			last.setPrevious(current);
			last.setNext(new DoubleLinkedListNode<T>());
			
			this.setLast(last);
		}
		
	}

	@Override
	public void removeFirst() {
		if (!this.head.isNIL()) {
			if (this.head.equals(last) && this.head.next.isNIL()) {
				this.last = new DoubleLinkedListNode<T>();
				this.head = this.getLast();
			} else {
				DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) this.head.getNext();
				newHead.setPrevious(new DoubleLinkedListNode<T>());
				this.setHead(newHead);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.head.isNIL()) {
			if (this.head.equals(last) && this.head.next.isNIL()) {
				this.last = new DoubleLinkedListNode<T>();
				this.head = this.getLast();
			}else  {
				this.setLast(last.getPrevious());
				this.last.setNext(new DoubleLinkedListNode<T>());
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (!this.head.isNIL()) {
			//Verifica se o elemento é o first.
			if (this.head.getData().equals(element)) {
				this.removeFirst();
			} else {
				
				DoubleLinkedListNode<T> current = (DoubleLinkedListNode<T>) this.head;
				if (!current.isNIL()) {
					//Caminha na lista até achar elemento procurado ou um nil.
					while (!current.isNIL() && !current.getData().equals(element)) {
						current = (DoubleLinkedListNode<T>) current.getNext();
					}
					//Verifica se current possui o elemento procurado e se o mesmo é o last.
					if (current.equals(this.last) && last.getData().equals(element)) {
						this.removeLast();
					} else if (current.getData() != null && current.getData().equals(element)) {
						DoubleLinkedListNode<T> next = (DoubleLinkedListNode<T>) current.getNext();
						current.getPrevious().setNext(next);
						next.setPrevious(current.getPrevious());
					}
				}
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
