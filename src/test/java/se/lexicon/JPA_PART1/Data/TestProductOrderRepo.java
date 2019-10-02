package se.lexicon.JPA_PART1.Data;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
	private OrderItem test;
	
	@Before
	public void setUp() {
		p1 = new Product("Eclipse", 99.99);
		testCustomer = new AppUser("Test", "Testsson", "test@lexicon.se");
		ProductOrder productOrder = new ProductOrder(LocalDate.of(2019, 10, 1), LocalTime.of(15, 45), testCustomer, new ArrayList<>());
		test = new OrderItem(1, p1, productOrder);
		productOrder.addItem(test);
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
	public void test_optional_of_find_by_order_date() {
		
		LocalDate testOrderDate = LocalDate.of(2019, 10, 1);
		
		Optional<ProductOrder> result = testObject.findByOrderDate(testOrderDate);
		
		assertTrue(result.isPresent());
		assertEquals(LocalDate.of(2019, 10, 1), result.get().getOrderDate());
	}
	
	@Test
	public void test_optional_of_find_product_id_by_items_list() {
		int id = p1.getProductId();
		int expectedSize = 1;
		
		Optional<ProductOrder> result = testObject.findByItemsProductProductId(id);
		
		assertTrue(result.isPresent());
		assertEquals(expectedSize, result.get().getItems().size());
	}
	
	@Test
	public void test_optional_find_product_name_by_items_list() {
		String name = p1.getProductName();
		int expectedSize = 1;
		
		Optional<ProductOrder> result = testObject.findByItemsProductProductName(name);
		
		assertTrue(result.isPresent());
		assertEquals(expectedSize, result.get().getItems().size());
	}
}
