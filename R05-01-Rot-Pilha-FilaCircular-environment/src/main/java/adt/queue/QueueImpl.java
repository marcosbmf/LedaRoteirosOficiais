package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i+1];
		}
		array[tail] = null;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!this.isFull()) {
			tail ++;
			array[tail] = element;
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T element = this.array[0];
			this.shiftLeft();
			tail--;
			return element;
		}
	}

}
