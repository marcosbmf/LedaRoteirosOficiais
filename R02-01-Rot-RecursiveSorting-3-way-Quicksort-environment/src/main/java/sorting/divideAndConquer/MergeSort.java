package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		Util.nullArrayChecker(array);
		Util.rangeCheck(array, leftIndex, rightIndex);
		Util.nullElementCheck(array, leftIndex, rightIndex);
		
		int tamVetor = rightIndex - leftIndex + 1;
		if (tamVetor >= 2) {
			int middle = (leftIndex + rightIndex)/2;
			
			this.sort(array, leftIndex, middle);
			this.sort(array, middle+1, rightIndex);
			
			this.merge(array, leftIndex, middle, rightIndex);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		
		
		T[] temp = (T[]) new Comparable[array.length];
		
		for(int i = leftIndex; i < rightIndex+1; i++ ) {
			temp[i] = array[i];
		}
		
		int i = leftIndex;
		int j = middle+1;
		int k = leftIndex;
		
		while (i < middle+1 && j < rightIndex+1) {
			if (temp[i].compareTo(temp[j]) < 0) {
				array[k] = temp[i];
				k++;
				i++;
			} else {
				array[k] = temp[j];
				k++;
				j++;
			}
		}
		
		while (i < middle+1) {
			array[k] = temp[i];
			k++;
			i++;
		}
		
		while (j < rightIndex+1) {
			array[k] = temp[j];
			k++;
			j++;
		}
		
	}

}
