package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.array[top];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.top < this.array.length - 1) {
			this.top++;
			this.array[top] = element;
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.top != -1) {
			T element = this.array[top];
			this.array[top] = null;
			this.top --;
			return element;
		} else {
			throw new StackUnderflowException();
		}
	}

}
