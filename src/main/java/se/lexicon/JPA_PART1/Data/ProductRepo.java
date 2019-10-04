package se.lexicon.JPA_PART1.Data;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{

	List<Product> findByProductNameStartsWithIgnoreCase(String productName);
	
}
