package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.simpleSorting.InsertionSort;

public class InsertionSortTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] vetorNull;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
		populaVetorNull(new Integer[5]);
		
		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		this.implementation = new InsertionSort<Integer>();
	}
	
	public void populaVetorNull(Integer[] arrayPadrao) {
		this.vetorNull = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
	
	@Test(expected=NullPointerException.class)
	public void testSort06(){
		genericTest(new Integer[] {1,2,3,4,null,4,3,2,1});
	}
	
	public void genericTest(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array, leftIndex, rightIndex);
		
		try {
		Arrays.sort(copy1, leftIndex, rightIndex+1);
		} catch (Exception e) {
			
		}
		Assert.assertArrayEquals(copy1, array);
	}
	
	@Test
	public void testSort07() {
		genericTest(vetorTamPar, 1, vetorTamPar.length - 2);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSort08() {
		genericTest(vetorTamPar, -3, vetorTamPar.length - 1);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSort09() {
		genericTest(vetorTamPar, 0, vetorTamPar.length + 1 );
	}
	
	@Test(expected=NullPointerException.class)
	public void testSort10() {
		genericTest(vetorNull, 1, vetorNull.length - 2);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSort11(){
		genericTest(new Integer[] {1,2,3,4,null,4,3,2,1}, 1, 7);
	}
	
	@Test
	public void testSort12(){
		genericTest(new Integer[] {1,2,3,4,9,4,3,2,null}, 0, 7);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSort13() {
		genericTest(vetorVazio, -3, 1);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSort14() {
		genericTest(vetorVazio, 0, vetorVazio.length - 2);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testSort15() {
		genericTest(vetorTamPar, vetorTamPar.length - 1, 3);
	}
}