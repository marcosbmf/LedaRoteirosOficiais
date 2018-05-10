package sorting.linearSorting;

import sorting.AbstractSorting;

public class CountingSort extends AbstractSorting<Integer> {

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
		
		Integer[] arrayResultado = new Integer[tamanhoArray];
		int[] arraySomaAcumulada = new int[maior + 1];
		
		//Conta os números do array
		int indice = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			indice = (int) array[i];
			arraySomaAcumulada[indice] ++;
		}
		
		//Soma acumulada
		for (int i = 1; i < arraySomaAcumulada.length; i++) {
			arraySomaAcumulada[i] += arraySomaAcumulada[i-1]; 
		}
		
		//Coloca cada elemento em sua posição correta.
		for (int i = leftIndex; i <= rightIndex; i++) {
			arraySomaAcumulada[(int) array[i]]--;
			indice = arraySomaAcumulada[(int) array[i]];
			arrayResultado[indice] = array[i]; 
		}
		
		for (int i = 0; i < arrayResultado.length; i++) {
			array[i+leftIndex] = arrayResultado[i];
		}
		
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
