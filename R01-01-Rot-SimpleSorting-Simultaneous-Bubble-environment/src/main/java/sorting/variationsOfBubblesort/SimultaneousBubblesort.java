package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		Util.rangeCheck(array, leftIndex, rightIndex);
		Util.nullElementCheck(array, leftIndex, rightIndex);

		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < rightIndex - leftIndex; i++) {
				if (array[leftIndex + i].compareTo(array[leftIndex + i + 1]) > 0) {
					Util.swap(array, leftIndex + i, leftIndex + i + 1);
					swap = true;
				}
				if (array[rightIndex - i].compareTo(array[rightIndex - i - 1]) < 0) {
					Util.swap(array, rightIndex - i, rightIndex - i - 1);
					swap = true;
				}
			}
		}

	}
}
