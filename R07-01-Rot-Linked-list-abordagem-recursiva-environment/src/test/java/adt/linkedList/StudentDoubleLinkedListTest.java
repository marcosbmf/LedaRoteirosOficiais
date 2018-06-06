package adt.linkedList;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}

	@Test
	public void testFinal2() {
		System.out.println("Teste 3:");
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3.remove(null);
		lista3.remove(5);
		lista3.removeFirst();
		lista3.removeLast();
		lista3.insert(null);
		lista3.insertFirst(null);
		System.out.println(lista3.size());
		System.out.println(lista3.search(15));
		System.out.println(lista3.search(null));
		lista3.insert(10);
		lista3.insert(5);
		lista3.insertFirst(-2);
		lista3.removeLast();
		lista3.removeLast();
		lista3.removeLast();
		lista3.removeLast();
		System.out.println(lista3.size());
		System.out.println(Arrays.toString(lista3.toArray()));
		System.out.println("Final");
	}
}