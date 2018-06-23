package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		int key;
		int probe = 0;
		if (this.isFull()) {
			throw new HashtableOverflowException();
		} else {
			while (probe < this.capacity()) {
				key = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if (this.table[key] == null || this.table[key].equals(deletedElement)) {
					this.table[key] = element;
					this.elements++;
					break;
				} else if (this.table[key].equals(element)) {
					break;
				} else {
					probe++;
					this.COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty()) {
			int key = indexOf(element);
			if (key != -1 && this.table[key] != null && this.table[key].equals(element)) {
				this.table[key] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int index = indexOf(element);
		T result = null;
		if (index != -1) {
			if (this.table[index] instanceof HashtableElement) {
				result = (T) this.table[index];
			}
		}
		
		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		int key;
		int probe = 0;
		
		if (!this.isEmpty()) {
			while (probe < this.capacity()) {
				key = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if (this.table[key] == null) {
					break;
				} else if (this.table[key].equals(element)) {
					index = key;
					break;
				} else {
					probe++;
				}
			}
		}
		return index;
	}
}
