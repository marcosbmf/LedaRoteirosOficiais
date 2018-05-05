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
			Util.nullArrayChecker(array);
			Util.rangeCheck(array, leftIndex, rightIndex);
			Util.nullElementCheck(array, leftIndex, rightIndex);
		} catch (Exception e) {
			return;
		}
		
		
		int tamVetor = rightIndex - leftIndex + 1;
		
		if (tamVetor <= 1) {
			return;
		}
		
		int pivotIndex = rightIndex;
		int i = leftIndex;
		boolean swap = false;
		
		/**
		 * Compara o pivot com elementos à sua esquerda e realiza trocas até que ele esteja em seu lugar.
		 */
		while(pivotIndex > i) {
			swap = false;
			if (array[i].compareTo(array[pivotIndex]) > 0) {
				swap = true;
				if (i == pivotIndex - 1) {
					Util.swap(array, pivotIndex, i);
					pivotIndex = i;
				} else {
					/**
					 * Primeira operação troca o pivot com o elemento a sua esquerda.
					 * Segunda operação atualiza a posição do pivot.
					 * Terceira operação troca o elemento que deveria estar à direita do pivot com o elemento trocado anteriormente.
					 * Resultado: o elemento que estava ao lado do pivot permanece à sua esquerda e o que deveria estar a sua direita agora está.
					 */
					Util.swap(array, pivotIndex, pivotIndex - 1);
					pivotIndex = pivotIndex - 1;
					Util.swap(array, pivotIndex+1, i);
				}
			}
			/**
			 * Caso não haja troca, atualiza o próximo elemento a ser comparado com o pivot.
			 */
			if(!swap) {
				i++;
			}
		}
		
		/**
		 * Realiza chamada recursiva para o quicksort de acordo com a posição final do pivot.
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
}
