package se.lexicon.JPA_PART1.Data;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.ProductOrder;

public interface ProductOrderRepo extends CrudRepository<ProductOrder, Integer> {

	Optional<ProductOrder> findByOrderDate(LocalDate orderDate);
	Optional<ProductOrder> findByItemsProductProductId(int productId);
	Optional<ProductOrder> findByItemsProductProductName(String productName);
}
