package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;
import util.ValidacaoArray;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		ValidacaoArray.rangeCheck(array, leftIndex, rightIndex);
		ValidacaoArray.nullElementCheck(array, leftIndex, rightIndex);

		for (int i = leftIndex; i < rightIndex; i++) {
			int elemIndex = i + 1;

			while (elemIndex > leftIndex && array[elemIndex].compareTo(array[elemIndex - 1]) < 0) {
				Util.swap(array, elemIndex, elemIndex - 1);
				elemIndex--;
			}
		}
	}
}
