package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		try {
			nullArrayChecker(array);
			rangeCheck(array, leftIndex, rightIndex);
			nullElementCheck(array, leftIndex, rightIndex);
		} catch (Exception e) {
			return;
		}

		int tamVetor = rightIndex - leftIndex + 1;

		if (tamVetor <= 1) {
			return;
		}

		/**
		 * Após o particionamento, salva a posição final do pivot.
		 */
		int pivotIndex = this.partition(array, leftIndex, rightIndex);

		/**
		 * Fase da divisão: Realiza chamada recursiva para o quicksort de acordo com a
		 * posição final do pivot.
		 */
		if (pivotIndex == leftIndex) {
			this.sort(array, pivotIndex + 1, rightIndex);
		} else if (pivotIndex == rightIndex) {
			this.sort(array, leftIndex, pivotIndex - 1);
		} else {
			this.sort(array, leftIndex, pivotIndex - 1);
			this.sort(array, pivotIndex + 1, rightIndex);
		}

	}

	/**
	 * Fase de conquista. Leva o pivot até sua posição correta no array.
	 * 
	 * @param array
	 * @param leftIndex
	 * @param rightIndex
	 * @return posição final do pivot no array.
	 */
	private Integer partition(T[] array, int leftIndex, int rightIndex) {

		int pivotIndex = rightIndex;
		int i = leftIndex;
		boolean swap = false;

		/**
		 * Compara o pivot com elementos à sua esquerda e realiza trocas até que ele
		 * esteja em seu lugar.
		 */
		while (pivotIndex > i) {
			swap = trySwap(array, pivotIndex, i);
			if (swap) {
				pivotIndex--;
			} else {
				i++;
			}
		}
		return pivotIndex;
	}

	/**
	 * Compara o pivot com o elemento a sua esquerda fornecido e realiza a troca
	 * caso ele seja maior que o pivot.
	 * 
	 * @param array
	 * @param pivotIndex
	 * @param elementIndex
	 * @return
	 */
	private boolean trySwap(T[] array, int pivotIndex, int elementIndex) {
		boolean swap = false;
		if (array[elementIndex].compareTo(array[pivotIndex]) > 0) {
			swap = true;
			if (elementIndex == pivotIndex - 1) {
				Util.swap(array, pivotIndex, elementIndex);
				pivotIndex = elementIndex;
			} else {
				Util.swap(array, pivotIndex, pivotIndex - 1);
				pivotIndex = pivotIndex - 1;
				Util.swap(array, pivotIndex + 1, elementIndex);
			}
		}
		return swap;
	}
	
	public void rangeCheck(Object[] array, int leftIndex, int rightIndex) {
		int length = array.length;
		if (length == 0) {
			if(leftIndex < 0 || (rightIndex != -1 && rightIndex != 0)){
				throw new ArrayIndexOutOfBoundsException();
			}
		}else if(leftIndex < 0 || rightIndex > length) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (leftIndex > rightIndex) {
			throw new UnsupportedOperationException();
		}
	}
	
	public void nullElementCheck(Object[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] == null) {
				throw new NullPointerException();
			}
		}
	}
	
	public void nullArrayChecker(Object[] array) {
		if (array == null) {
			throw new NullPointerException();
		}
	}
}
