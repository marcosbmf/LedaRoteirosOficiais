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
			nullArrayChecker(array);
			rangeCheck(array, leftIndex, rightIndex);
			nullElementCheck(array, leftIndex, rightIndex);
		} catch (Exception e) {
			return;
		}
		
		if (array.length <= 1) {
			return;
		}
		
		int tamanhoArray = rightIndex - leftIndex +1;
		int maior = getMaiorInteiro(array, leftIndex, rightIndex);
		int menor = getMenorInteiro(array, leftIndex, rightIndex);
		
		Integer[] arrayResultado = new Integer[tamanhoArray];
		int[] arraySomaAcumulada = new int[maior - menor + 1];
		
		//Conta os números do array
		int indice = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			indice = array[i] - menor;
			arraySomaAcumulada[indice] ++;
		}
		
		//Soma acumulada
		for (int i = 1; i < arraySomaAcumulada.length; i++) {
			arraySomaAcumulada[i] += arraySomaAcumulada[i-1]; 
		}
		
		//Coloca cada elemento em sua posição correta.
		for (int i = leftIndex; i <= rightIndex; i++) {
			arraySomaAcumulada[array[i] - menor]--;
			indice = arraySomaAcumulada[array[i] - menor];
			arrayResultado[indice] = array[i]; 
		}
		
		for (int i = 0; i < arrayResultado.length; i++) {
			array[i+leftIndex] = arrayResultado[i];
		}
		
	}

	private int getMenorInteiro(Integer[] array, int leftIndex, int rightIndex) {
		int menor = array[leftIndex];
		for(int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i].compareTo(menor) < 0) {
				menor = array[i];
			}
		}
		return menor;
	}

	private int getMaiorInteiro(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];
		for(int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		return maior;
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
