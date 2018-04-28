package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		Util.rangeCheck(array, leftIndex, rightIndex);
		Util.nullElementCheck(array, leftIndex, rightIndex);
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			T menor = array[i];
			int indexMenor = i;

			for (int j = i + 1; j <= rightIndex; j++) {
				if (array[j].compareTo(menor) < 0) {
					menor = array[j];
					indexMenor = j;
				}
			}
			Util.swap(array, i, indexMenor);
		}
	}
}
