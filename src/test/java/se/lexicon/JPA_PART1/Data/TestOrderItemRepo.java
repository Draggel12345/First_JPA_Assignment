package se.lexicon.JPA_PART1.Data;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
public class TestOrderItemRepo {

	@Autowired
	private OrderItemRepo testObject;
	private OrderItem orderItemTest;
	private AppUser testCustomer;
	private Product testProduct;
	private ProductOrder testProductOrder;
	
	@Before
	public void setUp() {
		testCustomer = new AppUser("Test", "Testsson", "test@lexicon.se");
		testProduct = new Product("Eclipse", 99.99);
		testProductOrder = new ProductOrder(LocalDate.of(2019, 10, 1), LocalTime.of(15, 15), testCustomer, new ArrayList<>());
		
		OrderItem orderItem = new OrderItem(5, testProduct, testProductOrder);
		orderItemTest = testObject.save(orderItem);
	}
	
	@Test
	public void test_save_orderItem_success() {
		
		assertNotNull(orderItemTest);
		assertTrue(orderItemTest.getOrderId() != 0);
		assertEquals(5, orderItemTest.getQuantity());
		assertEquals(testProduct, orderItemTest.getProduct());
		assertEquals(testProductOrder, orderItemTest.getProductOrder());
		
	}
	
}
