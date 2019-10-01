package se.lexicon.JPA_PART1.Entity;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestOrderItem {

	private OrderItem testObject;
	private AppUser user;
	private Product product;
	private ProductOrder order;
	
	@Before
	public void setUp() {
		user = new AppUser("Test", "Testsson", "test@lexicon.se");
		product = new Product("Eclipse", 99.99);
		order = new ProductOrder(LocalDate.of(2019, 10, 1), LocalTime.of(10, 0), user, new ArrayList<>());
		testObject = new OrderItem(1, 5, product, order);
	}
	
	@Test
	public void testObject_succsess_created() {
		assertEquals(1, testObject.getOrderId());
		assertEquals(5, testObject.getQuantity());
		assertEquals(product, testObject.getProduct());
		assertEquals(order, testObject.getProductOrder());
	}
	
	@Test
	public void test_calculate_method() {
		product.setPrice(100.00);
		testObject.setQuantity(5);
		
		double expected = 500.00;
		double actual = testObject.calculatePrice();
		
		assertTrue(expected == actual);
		assertEquals(expected, actual, 0);
	}
	
	@Test
	public void test_equals_and_hashCode() {
		OrderItem copy = new OrderItem(1, 5, product, order);
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		
		assertTrue(
				toString.contains("1") &&
				toString.contains("5") &&
				toString.contains(product.toString())
				);
	}
}
