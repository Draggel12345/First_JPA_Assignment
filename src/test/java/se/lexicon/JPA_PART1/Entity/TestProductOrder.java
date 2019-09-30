package se.lexicon.JPA_PART1.Entity;

import java.time.LocalDateTime;
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
		testObject = new ProductOrder(1, LocalDateTime.of(2019, 9, 30, 11, 0), testUser, new ArrayList<>());
	}
	
	@Test
	public void testObject_success_created() {
		assertEquals(1, testObject.getProductOrderId());
		assertEquals(LocalDateTime.of(2019, 9, 30, 11, 0), testObject.getOrderDateTime());
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
		OrderItem test1 = new OrderItem();
		OrderItem test2 = new OrderItem();
		
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
	public void test_equals_and_hashCode() {
		ProductOrder copy = new ProductOrder(1, LocalDateTime.of(2019, 9, 30, 11, 0), testUser, new ArrayList<>());
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		
		assertTrue(toString.contains("1") &&
				   toString.contains("2019-09-30T11:00") &&
				   toString.contains(testUser.toString()) &&
				   toString.contains("")
				);
		
	}
}
