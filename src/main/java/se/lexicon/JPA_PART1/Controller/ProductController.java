package se.lexicon.JPA_PART1.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.JPA_PART1.Data.ProductRepo;
import se.lexicon.JPA_PART1.Entity.Product;

@RestController
public class ProductController {

	private ProductRepo productRepo;

	@Autowired
	public ProductController(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@GetMapping("api/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = (List<Product>) productRepo.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@PostMapping("api/products")
	public ResponseEntity<Product> creat(@RequestBody Product product) {
		if(product == null) {
			throw new IllegalArgumentException();
		}
		return ResponseEntity.ok(productRepo.save(product));
	}
	
	@GetMapping("api/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") int id) {
		Optional<Product> optional = productRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("api/products/{productId}")
	public ResponseEntity<Product> update(@PathVariable("productId") int id, @RequestBody Product updated) {
		Product old = productRepo.findById(id).orElseThrow(IllegalArgumentException::new);
		if(id != updated.getProductId()) {
			throw new SecurityException();
		}
		
		old.setProductName(updated.getProductName());
		return ResponseEntity.ok(productRepo.save(old));
	}
	
	@GetMapping("api/products/productName")
	public ResponseEntity<Product> findByProductName(@RequestParam("productName") String productName) {
		if(productName == null) {
			throw new IllegalArgumentException();
		}
	
		Optional<Product> optional = productRepo.findByProductNameStartsWithIgnoreCase(productName);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}
}
