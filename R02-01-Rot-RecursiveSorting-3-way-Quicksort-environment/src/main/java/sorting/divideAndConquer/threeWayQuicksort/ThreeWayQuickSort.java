package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot, particionamos
	 * o array colocando os menores a esquerda do pivot e os maiores a direita do
	 * pivot, e depois aplicamos a mesma estrategia recursivamente na particao a
	 * esquerda do pivot e na particao a direita do pivot.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do
	 * quicksort pode ser otimizada para lidar de forma mais eficiente com isso.
	 * Essa melhoria eh conhecida como quicksort tree way e consiste da seguinte
	 * ideia: - selecione o pivot e particione o array de forma que * arr[l..i]
	 * contem elementos menores que o pivot * arr[i+1..j-1] contem elementos iguais
	 * ao pivot. * arr[j..r] contem elementos maiores do que o pivot.
	 * 
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh
	 * feito recursivamente.
	 **/
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
		 * O array é enviado para o particionamento, fase da conquista.
		 */
		Integer[] pivotInterval = this.particionamento(array, leftIndex, rightIndex);
		int firstPivotIndex = pivotInterval[0];
		int lastPivotIndex = pivotInterval[1];

		/**
		 * Realiza chamada recursiva para o quicksort de acordo com a posição final do
		 * pivot. Fase da divisão.
		 */
		if (firstPivotIndex == leftIndex) {
			if (lastPivotIndex < rightIndex) {
				this.sort(array, lastPivotIndex + 1, rightIndex);
			} else {
				return;
			}
		} else if (lastPivotIndex == rightIndex) {
			if (firstPivotIndex > leftIndex) {
				this.sort(array, leftIndex, firstPivotIndex - 1);
			} else {
				return;
			}
		} else {
			this.sort(array, leftIndex, firstPivotIndex - 1);
			this.sort(array, lastPivotIndex + 1, rightIndex);
		}
	}

	private Integer[] particionamento(T[] array, int leftIndex, int rightIndex) {

		int firstPivotIndex = rightIndex;
		int lastPivotIndex = rightIndex;
		int i = leftIndex;
		boolean swap = false;

		/**
		 * Compara o pivot com elementos à sua esquerda e realiza trocas até que ele
		 * esteja em seu lugar.
		 */
		while (firstPivotIndex > i && firstPivotIndex > leftIndex) {
			swap = false;
			if (array[i].compareTo(array[firstPivotIndex]) > 0) {
				swap = true;
				if (i == firstPivotIndex - 1) {
					// Verifica se elemento está ao lado do primeiro pivot.
					if (firstPivotIndex == lastPivotIndex) {
						// Caso esteja e só haja um pivot, apenas é realizada a troca e indices são
						// atualizados.
						Util.swap(array, firstPivotIndex, i);
						firstPivotIndex--;
						lastPivotIndex--;
					} else {
						// No outro caso, o elemento é trocado com o último pivot e os indices são
						// atualizados.
						Util.swap(array, lastPivotIndex, i);
						firstPivotIndex--;
						lastPivotIndex--;
					}
				} else {
					/**
					 * Caso elemento não esteja ao lado do primeiro pivot:
					 * 
					 * Primeira operação troca o último pivot com o elemento a esquerda do primeiro.
					 * Segunda operação atualiza a posição dos pivots. Terceira operação troca o
					 * elemento que deveria estar à direita dos pivots com o elemento trocado
					 * anteriormente. Resultado: o elemento que estava ao lado do pivot permanece à
					 * sua esquerda e o que deveria estar a sua direita agora está.
					 */
					Util.swap(array, lastPivotIndex, firstPivotIndex - 1);
					firstPivotIndex--;
					lastPivotIndex--;
					Util.swap(array, lastPivotIndex + 1, i);
				}
			} else if (array[i].compareTo(array[firstPivotIndex]) == 0) {
				//Caso o elemento seja um pivot.
				swap = true;
				Util.swap(array, firstPivotIndex - 1, i);
				firstPivotIndex--;
			}

			/**
			 * Caso não haja troca, atualiza o próximo elemento a ser comparado com o pivot.
			 */
			if (!swap) {
				i++;
			}
		}

		return new Integer[] { firstPivotIndex, lastPivotIndex };
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
