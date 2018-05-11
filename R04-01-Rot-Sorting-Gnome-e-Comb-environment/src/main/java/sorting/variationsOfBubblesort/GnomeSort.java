package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

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

		// Posição inicial do gnomo.
		int gnome = leftIndex;

		while (gnome != rightIndex) {
			/*
			 * Gnomo compara o elemento em que ele está com o próximo caso haja necessidade
			 * de realizar troca, ele volta.
			 */
			if (array[gnome].compareTo(array[gnome + 1]) > 0) {
				Util.swap(array, gnome, gnome + 1);
				gnome--;
			} else {
				gnome++;
			}

			if (gnome < 0) {
				gnome = 0;
			}
		}

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
