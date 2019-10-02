package se.lexicon.JPA_PART1.Entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestProductOrder {

	private ProductOrder testObject;
	private AppUser testUser;
	
	@Before
	public void setUp() {
		testUser = new AppUser("Test", "Testsson", "test@lexicon.se");
		testObject = new ProductOrder(1, LocalDate.of(2019, 9, 30), LocalTime.of(11, 0) , testUser, new ArrayList<>());
	}
	
	@Test
	public void testObject_success_created() {
		assertEquals(1, testObject.getProductOrderId());
		assertEquals(LocalDate.of(2019, 9, 30), testObject.getOrderDate());
		assertEquals(LocalTime.of(11, 0), testObject.getOrderTime());
		assertEquals(testUser, testObject.getCustomer());
		assertEquals(new ArrayList<>(), testObject.getItems());
	}
	
	@Test
	public void test_adding_to_list_of_items() {
		OrderItem test = new OrderItem();
		
		testObject.addItem(test);
		
		int expectedSizeOfList = 1;
		int actuallSizeOfList = testObject.getItems().size();
		
		assertTrue(expectedSizeOfList == actuallSizeOfList);
		assertEquals(expectedSizeOfList, actuallSizeOfList);
	}
	
	@Test
	public void test_removing_from_list_of_items() {
		OrderItem test1 = new OrderItem(1, null, testObject);
		OrderItem test2 = new OrderItem(2, null, testObject);
		
		testObject.addItem(test1);
		testObject.addItem(test2);
		
		int expectedSize1 = 2;
		int actuallSize1 = testObject.getItems().size();
		
		assertTrue(expectedSize1 == actuallSize1);
		assertEquals(expectedSize1, actuallSize1);
		
		testObject.removeItem(test1);
		
		int expectedSize2 = 1;
		int actuallSize2 = testObject.getItems().size();
		
		assertTrue(expectedSize2 == actuallSize2);
		assertEquals(expectedSize2, actuallSize2);
	}
	
	@Test
	public void test_total_product_price_from_list_of_items() {
		Product prod1 = new Product("sak1", 5.0);
		Product prod2 = new Product("sak2", 3.0);
		
		OrderItem item1 = new OrderItem(2, prod1, testObject);
		OrderItem item2 = new OrderItem(1, prod2, testObject);
		
		testObject.getItems().add(item1);
		testObject.getItems().add(item2);
		
		double expectedTotalPrice = 13.00;
		double actualTotalPrice = testObject.totalProductPrice();
		
		assertTrue(expectedTotalPrice == actualTotalPrice);
		assertEquals(expectedTotalPrice, actualTotalPrice, 0);
		
	}
	
	@Test
	public void test_equals_and_hashCode() {
		ProductOrder copy = new ProductOrder(1, LocalDate.of(2019, 9, 30), LocalTime.of(11, 0), testUser, new ArrayList<>());
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		
		assertTrue(toString.contains("1") &&
				   toString.contains("2019-09-30") &&
				   toString.contains("11:00") &&
				   toString.contains(testUser.toString()) &&
				   toString.contains("")
				);
	}
}
