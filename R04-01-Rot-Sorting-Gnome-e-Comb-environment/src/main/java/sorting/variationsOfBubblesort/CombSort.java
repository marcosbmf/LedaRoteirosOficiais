package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// Verificações iniciais
		try {
			this.nullArrayChecker(array);
			this.rangeCheck(array, leftIndex, rightIndex);
			this.nullElementCheck(array, leftIndex, rightIndex);
		} catch (Exception e) {
			return;
		}

		if (array.length <= 1) {
			return;
		}

		// Gap inicial
		int gap = (int) ((array.length) / 1.25);
		if (gap == 0) {
			gap = 1;
		}

		while (gap > 1) {
			// Faz a comparação dos elementos utilizando o gap até quando possível.
			for (int i = leftIndex; i < rightIndex; i++) {
				if (i + gap <= rightIndex) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
					}
				} else {
					i = rightIndex + 1;
				}
			}

			// Atualiza o gap.
			gap = (int) (gap / 1.25);

		}

		this.bubbleSort(array, leftIndex, rightIndex);
	}

	private void bubbleSort(T[] array, int leftIndex, int rightIndex) {
		boolean troca;
		do {
			troca = false;
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					Util.swap(array, i, i + 1);
					troca = true;
				}
			}
		} while (troca);
	}

	// Métodos para verificações

	private void rangeCheck(Object[] array, int leftIndex, int rightIndex) {
		int length = array.length;
		if (length == 0) {
			if (leftIndex < 0 || (rightIndex != -1 && rightIndex != 0)) {
				throw new ArrayIndexOutOfBoundsException();
			}
		} else if (leftIndex < 0 || rightIndex > length) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (leftIndex > rightIndex) {
			throw new UnsupportedOperationException();
		}
	}

	private void nullElementCheck(Object[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] == null) {
				throw new NullPointerException();
			}
		}
	}

	private void nullArrayChecker(Object[] array) {
		if (array == null) {
			throw new NullPointerException();
		}
	}
}
