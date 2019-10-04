package se.lexicon.JPA_PART1.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.ProductOrder;

public interface ProductOrderRepo extends CrudRepository<ProductOrder, Integer> {

	List<ProductOrder> findByOrderDate(LocalDate orderDate);
	List<ProductOrder> findByItemsProductProductId(int productId);
	List<ProductOrder> findByItemsProductProductNameStartsWithIgnoreCase(String productName);
	Optional<ProductOrder> findByCustomerUserId(int userId);
}
