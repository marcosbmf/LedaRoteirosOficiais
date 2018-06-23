package adt.bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class TestTree {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
	}
	
	@Test
	public void insertTest() {
		this.fillTree();
		assertEquals(4, tree.height());
		int size = 12;
		assertEquals(size, tree.size());
	}
	
	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}
	
	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}
	
	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
		
		assertEquals(new Integer(23), tree.predecessor(67).getData());
	}
	
	@Test
	public void preOrder() {
		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		Integer[] Order = new Integer[] { 40, 0, 2, 5, 34, 12, 9, 67, 76, 232, 23, 6 };
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232
		System.out.println(Arrays.toString(tree.preOrder()));
		System.out.println("Order "+ Arrays.toString(tree.order()));
		System.out.println(Arrays.toString(tree.postOrder()));
		assertArrayEquals(preOrder, tree.preOrder());
	}

}
