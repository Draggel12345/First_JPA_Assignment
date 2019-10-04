package se.lexicon.JPA_PART1.Data;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.JPA_PART1.Entity.Product;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TestProductRepo {

	@Autowired
	private ProductRepo testObject;
	private Product testProduct;
	
	@Before
	public void setUp() {
		Product product = new Product("Eclipse", 99.99);
		testProduct = testObject.save(product);
	}
	
	@Test
	public void test_save_product_success() {
		
		assertNotNull(testProduct);
		assertTrue(testProduct.getProductId() != 0);
		assertEquals("Eclipse" , testProduct.getProductName());
		assertEquals(99.99, testProduct.getPrice(), 0);
	}
	
	@Test
	public void given_ecli_should_return_Eclipse_and_list_size_of_1() {
		
		String product = "ecli";
		int expectedSize = 1;
		
		List<Product> result = testObject.findByProductNameStartsWithIgnoreCase(product);
		
		assertTrue(result.contains(testProduct));
		assertEquals(expectedSize, result.size());
		
	}
	
}
