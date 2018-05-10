package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
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
		
		int tamanhoArray = rightIndex - leftIndex +1;
		int maior = getMaiorInteiro(array, leftIndex, rightIndex);
		int menor = getMenorInteiroNegativo(array, leftIndex, rightIndex);
		
		Integer[] arrayResultado = new Integer[tamanhoArray];
		int[] arraySomaAcumuladaPositivo = new int[maior + 1];
		int[] arraySomaAcumuladaNegativo = new int[(-1*menor) + 1];
		
		//Conta os números do array
		int indice = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			indice = array[i];
			if (indice < 0) {
				arraySomaAcumuladaNegativo[-1*indice] ++;
			}else {
				arraySomaAcumuladaPositivo[indice]++;
			}
		}
		
		//Soma acumulada
		for (int i = arraySomaAcumuladaNegativo.length-2; i >= 0; i--) {
			arraySomaAcumuladaNegativo[i] += arraySomaAcumuladaNegativo[i+1]; 
		}
		
		arraySomaAcumuladaPositivo[0] += arraySomaAcumuladaNegativo[0];
		
		for (int i = 1; i < arraySomaAcumuladaPositivo.length; i++) {
			arraySomaAcumuladaPositivo[i] += arraySomaAcumuladaPositivo[i-1]; 
		}
		
		//Coloca cada elemento em sua posição correta.
		int elemento;
		for (int i = leftIndex; i <= rightIndex; i++) {
			elemento = array[i];
			if (elemento >= 0) {
				arraySomaAcumuladaPositivo[elemento]--;
				indice = arraySomaAcumuladaPositivo[elemento];
				arrayResultado[indice] = elemento;
			} else {
				arraySomaAcumuladaNegativo[elemento*-1]--;
				indice = arraySomaAcumuladaNegativo[elemento*-1];
				arrayResultado[indice] = elemento;
			}
		}
		
		for (int i = 0; i < arrayResultado.length; i++) {
			array[i+leftIndex] = arrayResultado[i];
		}
		
	}

	private int getMaiorInteiro(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = array[leftIndex];
		for(int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		if (maior < 0){
			return 0;
		}
		return (int) maior;
	}
	
	private int getMenorInteiroNegativo(Integer[] array, int leftIndex, int rightIndex) {
		Integer menor = 0;
		for(int i = leftIndex; i < rightIndex + 1; i++) {
			if (array[i].compareTo(menor) < 0 ) {
				menor = array[i];
			}
		}
		return (int) menor;
	}
	
	private void rangeCheck(Object[] array, int leftIndex, int rightIndex) {
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
