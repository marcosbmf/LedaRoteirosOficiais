package sorting.divideAndConquer;

import sorting.AbstractSorting;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		try {
			nullArrayChecker(array);
			rangeCheck(array, leftIndex, rightIndex);
			nullElementCheck(array, leftIndex, rightIndex);
		} catch (Exception e) {
			return;
		}
		
		this.mergeSort(array, leftIndex, rightIndex);
	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		
		int tamVetor = rightIndex - leftIndex + 1;
		
		/**
		 * Fase de divisão:
		 * O vetor é dividido até ter um tamanho 2, onde passará por mais uma divisão e não poderá ser dividido de novo.
		 */
		if (tamVetor > 1) {
			int middle = (leftIndex + rightIndex)/2;
			
			this.sort(array, leftIndex, middle);
			this.sort(array, middle+1, rightIndex);
			
			/**
			 * Fase de conquista:
			 * Após a divisão o vetor começa a ser ordenado pedaço a pedaço.
			 */
			this.merge(array, leftIndex, middle, rightIndex);
		}
	}

	@SuppressWarnings("unchecked")
	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {

		T[] leftPart = (T[]) new Comparable[middle - leftIndex + 1];
		T[] rightPart = (T[]) new Comparable[rightIndex - middle];
		
		// Cópia da parte do vetor a ser ordenada
		int tempIndex = leftIndex;
		for (int i = 0; i < leftPart.length; i++) {
			leftPart[i] = array[tempIndex];
			tempIndex++;
		}
		
		tempIndex = middle + 1;
		for (int i = 0; i < rightPart.length; i++) {
			rightPart[i] = array[tempIndex];
			tempIndex++;
		}
		
		
		int i = 0;
		int j = 0;
		int k = leftIndex;

		// Adiciona os elementos no array de acordo comparando elemento a elemento do
		// primeiro vetor com o segundo.
		while (i < leftPart.length && j < rightPart.length) {
			if (leftPart[i].compareTo(rightPart[j]) < 0) {
				array[k] = leftPart[i];
				k++;
				i++;
			} else {
				array[k] = rightPart[j];
				k++;
				j++;
			}
		}

		// Finaliza adicionando os elementos restantes do vetor que não foi terminado.
		while (i < leftPart.length) {
			array[k] = leftPart[i];
			k++;
			i++;
		}

		while (j < rightPart.length) {
			array[k] = rightPart[j];
			k++;
			j++;
		}

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
