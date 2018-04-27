package util;

public class ValidacaoArray {
	
	public static void rangeCheck(Object[] array, int leftIndex, int rightIndex) {
		int length = array.length;
		if (length == 0) {
			if(leftIndex < 0 || (rightIndex != -1 && rightIndex != 0)){
				throw new ArrayIndexOutOfBoundsException();
			}
		}else if(leftIndex < 0 || rightIndex > length || leftIndex > rightIndex) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	public static void nullElementCheck(Object[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] == null) {
				throw new NullPointerException();
			}
		}
	}
}
