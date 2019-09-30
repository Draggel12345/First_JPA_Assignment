package se.lexicon.JPA_PART1.Entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestProduct {

	private Product testObject;
	
	@Before
	public void setUp() {
		testObject = new Product(1, "Eclipse", 99.99);
	}
	
	@Test
	public void testObject_sucsess_created() {
		assertEquals(1, testObject.getProductId());
		assertEquals("Eclipse", testObject.getProductName());
		assertEquals(99.99, testObject.getPrice(), 0);
	}
	
	@Test
	public void test_equals_and_hashCode() {
		Product copy = new Product(1, "Eclipse", 99.99);
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		
		assertTrue(
				toString.contains("1") &&
				toString.contains("Eclipse") &&
				toString.contains("99.99")
				);
	}
}
