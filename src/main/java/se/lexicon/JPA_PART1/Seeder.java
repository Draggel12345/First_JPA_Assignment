package se.lexicon.JPA_PART1;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import se.lexicon.JPA_PART1.Data.ProductRepo;
import se.lexicon.JPA_PART1.Entity.Product;

@Component
public class Seeder implements CommandLineRunner {

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public void run(String... args) throws Exception {
		creatProducts();
	}

	public void creatProducts() {
		
		List<Product> products = Arrays.asList(
				new Product("Game Boy", 500),
				new Product("Game Cub", 750),
				new Product("Xbox", 1000),
				new Product("Play Station", 1500),
				new Product("PC", 2000)
				);
		
		productRepo.saveAll(products);
	}
	
}
