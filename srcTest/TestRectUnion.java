import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRectUnion {
	@Test
	public void test1() {
		Zone2D area = new Zone2D(0, 0, 6, 4, 0);
		Zone2D add0 = new Zone2D(0, 1, 4, 2, 0);
		Node root = new Node(area);

		TreeRectUnionUtils union = new TreeRectUnionUtils();
		union.add(root, add0);

		System.out.println("root = " + root);

		Zone2D check0 = new Zone2D(1, 1, 2, 1, 0);
		Zone2D check1 = new Zone2D(3, 2, 3, 2, 0);

		assertTrue(union.contains(root, check0));
		assertFalse(union.contains(root, check1));
	}

	@Test
	public void test2() {
		Zone2D area = new Zone2D(0, 0, 6, 4, 0);
		Zone2D add0 = new Zone2D(0, 1, 4, 2, 0);
		Zone2D add1 = new Zone2D(4, 0, 2, 2, 0);
		Node root = new Node(area);

		TreeRectUnionUtils union = new TreeRectUnionUtils();
		union.add(root, add0);
		union.add(root, add1);

		System.out.println("root = " + root);

		Zone2D check0 = new Zone2D(1, 1, 2, 1, 0);
		Zone2D check1 = new Zone2D(3, 2, 3, 2, 0);
		Zone2D check2 = new Zone2D(3, 1, 3, 1, 0);

		assertTrue(union.contains(root, check0));
		assertFalse(union.contains(root, check1));
		assertTrue(union.contains(root, check2));
	}

	@Test
	public void test3() {
		Zone2D area = new Zone2D(0, 0, 6, 4, 0);
		Zone2D add0 = new Zone2D(0, 1, 4, 2, 0);
		Zone2D add1 = new Zone2D(4, 0, 2, 2, 0);
		Zone2D del2 = new Zone2D(2, 0, 2, 2, 0);
		Node root = new Node(area);

		TreeRectUnionUtils union = new TreeRectUnionUtils();
		union.add(root, add0);
		union.add(root, add1);
		union.remove(root, del2);

		System.out.println("root = " + root);

		Zone2D check0 = new Zone2D(1, 1, 2, 1, 0);
		Zone2D check1 = new Zone2D(3, 2, 3, 2, 0);
		Zone2D check2 = new Zone2D(3, 1, 3, 1, 0);
		Zone2D check3 = new Zone2D(1, 2, 3, 1, 0);

		assertFalse(union.contains(root, check0));
		assertFalse(union.contains(root, check1));
		assertFalse(union.contains(root, check2));
		assertTrue(union.contains(root, check3));
	}
}
