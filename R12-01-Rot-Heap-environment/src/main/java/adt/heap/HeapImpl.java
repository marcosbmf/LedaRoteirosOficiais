package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse comparator.
	 * Assim os metodos da heap não precisam saber se vai funcionar como max-heap
	 * ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (T elem : this.heap) {
			if (elem != null) {
				resp.add(elem);
			}
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int left;
		int right;
		int largest = position;
		if (heap[position] != null && hasOneChild(position)) {
			if (hasLeft(position) && hasRight(position)) {
				left = this.left(position);
				right = this.right(position);

				if (this.compare(this.heap[left], this.heap[right]) > 0) {
					largest = left;
				} else {
					largest = right;
				}
			} else {
				if (this.hasLeft(position)) {
					largest = this.left(position);
				} else {
					largest = this.right(position);
				}
			}

			if (this.compare(this.heap[largest], this.heap[position]) > 0) {
				Util.swap(this.heap, largest, position);
				this.heapify(largest);
			}
		}
	}

	private boolean hasParent(int i) {
		return this.parent(i) <= this.index && i > 0;
	}

	private boolean hasLeft(int i) {
		return this.left(i) <= this.index;
	}

	private boolean hasRight(int i) {
		return this.right(i) <= this.index;
	}

	private boolean hasOneChild(int i) {
		return this.hasLeft(i) || this.hasRight(i);
	}

	//Caso comparator seja null, funciona como min-heap
	private int compare(T o1, T o2) {
		int compare;

		if (this.comparator != null) {
			compare = this.comparator.compare(o1, o2);
		} else {
			compare = o2.compareTo(o1);
		}

		return compare;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if (element != null) {
			int insertPos = index + 1;
			
			this.heap[insertPos] = element;
			index++;

			while (this.hasParent(insertPos) && this.compare(element, this.heap[this.parent(insertPos)]) > 0) {
				Util.swap(heap, insertPos, this.parent(insertPos));
				insertPos = this.parent(insertPos);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = Arrays.copyOf(array, array.length);
		this.index = array.length - 1;
		for (int i = array.length / 2; i >= 0; i--) {
			this.heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T element = null;
		if (index >= 0) {
			element = this.rootElement();
			this.heap[0] = null;
			Util.swap(this.heap, 0, index);
			this.index--;
			this.heapify(0);
		}
		return element;
	}

	@Override
	public T rootElement() {
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		T[] sorted = Arrays.copyOf(array, array.length);
		this.buildHeap(array);
		if (this.heap[0].compareTo(this.heap[index]) > 0) {
			// MaxHeap
			for (int i = array.length - 1; i >= 0; i--) {
				sorted[i] = this.extractRootElement();
			}
		} else {
			// MinHeap
			for (int i = 0; i < array.length; i++) {
				sorted[i] = this.extractRootElement();
			}
		}
		return sorted;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
