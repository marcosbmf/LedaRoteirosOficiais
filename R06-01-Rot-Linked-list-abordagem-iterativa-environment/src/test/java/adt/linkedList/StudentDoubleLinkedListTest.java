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
		lista1 = new DoubleLinkedListImpl<Integer>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new DoubleLinkedListImpl<Integer>();
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
	public void test() {
		lista3.insertFirst(new Integer(1));
		lista3.insert(new Integer(1));
		lista3.insert(new Integer(1));
		lista3.insert(new Integer(2));
		lista3.insert(new Integer(3));
		lista3.insert(new Integer(4));
		System.out.println(Arrays.toString(lista3.toArray()));
		lista3.removeFirst();
		System.out.println(Arrays.toString(lista3.toArray()));
		System.out.println(Arrays.toString(lista3.toArray()));
		System.out.println(lista3.search(1));
		System.out.println(lista3.search(0));
		System.out.println(lista3.search(2));
		System.out.println(lista3.search(3));
		System.out.println(lista3.search(4));
		System.out.println(Arrays.toString(lista3.toArray()));
		lista3.remove(1);
		lista3.remove(1);
		lista3.remove(4);
		lista3.remove(2);
		System.out.println(Arrays.toString(lista3.toArray()));
		
		
	}
}