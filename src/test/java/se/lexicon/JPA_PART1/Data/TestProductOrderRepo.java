package se.lexicon.JPA_PART1.Data;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.JPA_PART1.Entity.AppUser;
import se.lexicon.JPA_PART1.Entity.OrderItem;
import se.lexicon.JPA_PART1.Entity.Product;
import se.lexicon.JPA_PART1.Entity.ProductOrder;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TestProductOrderRepo {

	
	@Autowired
	private ProductOrderRepo testObject;
	private ProductOrder testProductOrder;
	private AppUser testCustomer;
	private Product p1;
	private OrderItem testOrder;
	
	@Before
	public void setUp() {
		p1 = new Product("Eclipse", 99.99);
		testCustomer = new AppUser("Test", "Testsson", "test@lexicon.se");
		ProductOrder productOrder = new ProductOrder(LocalDate.of(2019, 10, 1), LocalTime.of(15, 45), testCustomer, new ArrayList<>());
		testOrder = new OrderItem(1, p1, productOrder);
		productOrder.addItem(testOrder);
		testProductOrder = testObject.save(productOrder);
	}
	
	@Test
	public void test_save_product_order_success() {
		assertNotNull(testProductOrder);
		assertTrue(testProductOrder.getProductOrderId() != 0);
		assertEquals(LocalDate.of(2019, 10, 1), testProductOrder.getOrderDate());
		assertEquals(LocalTime.of(15, 45), testProductOrder.getOrderTime());
		assertEquals(testCustomer, testProductOrder.getCustomer());
		assertNotNull(testProductOrder);
	}	
	
	@Test
	public void test_find_by_order_date_list_should_return_size_of_1() {
		
		LocalDate testOrderDate = LocalDate.of(2019, 10, 1);
		int expectedSize = 1;
		
		List<ProductOrder> result = testObject.findByOrderDate(testOrderDate);
		
		assertTrue(result.contains(testProductOrder));
		assertEquals(expectedSize, result.size());
	}
	
	@Test
	public void test_find_product_id_by_items_list_should_return_size_of_1() {
		int id = p1.getProductId();
		int expectedSize = 1;
		
		List<ProductOrder> result = testObject.findByItemsProductProductId(id);
		
		assertTrue(result.contains(testProductOrder));
		assertEquals(expectedSize, result.size());
	}
	
	@Test
	public void test_find_product_name_by_items_list_should_return_size_of_1() {
		String name = "ecli";
		int expectedSize = 1;
		
		List<ProductOrder> result = testObject.findByItemsProductProductNameStartsWithIgnoreCase(name);
		
		assertTrue(result.contains(testProductOrder));
		assertEquals(expectedSize, result.size());
	}
	
	@Test
	public void test_given_testUser_id_should_return_optional_of_user_id() {
		
		int testId = testCustomer.getUserId();
		
		Optional<ProductOrder> result = testObject.findByCustomerUserId(testId);
		
		assertTrue(result.isPresent());
		assertEquals(testId, result.get().getCustomer().getUserId());
	}
}
