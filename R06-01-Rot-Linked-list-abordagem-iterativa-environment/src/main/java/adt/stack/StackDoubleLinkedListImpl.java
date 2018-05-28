package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!this.isFull()) {
			this.top.insertFirst(element);
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!this.isEmpty()) {
			T top = this.top();
			this.top.removeFirst();
			return top;
		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) top).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return this.top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
