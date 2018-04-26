package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;
import util.ValidacaoArray;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		ValidacaoArray.rangeCheck(array, leftIndex, rightIndex);
		ValidacaoArray.nullElementCheck(array, leftIndex, rightIndex);

		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < rightIndex - leftIndex; i++) {
				if (array[leftIndex + i].compareTo(array[leftIndex + i + 1]) > 0) {
					Util.swap(array, leftIndex + i, leftIndex + i + 1);
					swap = true;
				}
			}
		}
	}
}
