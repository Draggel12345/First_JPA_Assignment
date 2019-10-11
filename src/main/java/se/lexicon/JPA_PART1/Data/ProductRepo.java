package se.lexicon.JPA_PART1.Data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{

	Optional<Product> findByProductNameStartsWithIgnoreCase(String productName);
	
}
