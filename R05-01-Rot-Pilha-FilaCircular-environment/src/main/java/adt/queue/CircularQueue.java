package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		this.array = (T[]) new Object[size];
		this.head = -1;
		this.tail = -1;
		this.elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!this.isFull()) {
			if (this.isEmpty()) {
				head = tail + 1;
			}
			tail++;
			this.indexCorrection();
			elements ++;
			this.array[tail] = element;
		} else {
			throw new QueueOverflowException();
		}
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T element= this.array[this.head];
			this.array[this.head] = null;
			this.head ++;
			this.indexCorrection();
			this.elements --;
			return element;
		}
	}

	@Override
	public T head() {
		if (!this.isEmpty()) {
			return this.array[this.head];
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}
	
	private void indexCorrection() {
		if (this.tail > this.array.length - 1) {
			this.tail = this.tail - array.length;
		}
		
		if (this.head > this.array.length - 1) {
			this.head = this.head - array.length;
		}
		
	}

}