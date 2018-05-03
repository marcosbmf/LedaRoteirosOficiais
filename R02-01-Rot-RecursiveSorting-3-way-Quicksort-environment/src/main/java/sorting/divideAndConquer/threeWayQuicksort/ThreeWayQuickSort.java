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
		Util.nullArrayChecker(array);
		Util.rangeCheck(array, leftIndex, rightIndex);
		Util.nullElementCheck(array, leftIndex, rightIndex);

		int tamVetor = rightIndex - leftIndex + 1;

		if (tamVetor <= 1) {
			return;
		}

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
						firstPivotIndex = i;
						lastPivotIndex = i;
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

		/**
		 * Realiza chamada recursiva para o quicksort de acordo com a posição final do
		 * pivot.
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
}
