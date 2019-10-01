package se.lexicon.JPA_PART1.Data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.OrderItem;

public interface OrderItemRepo extends CrudRepository<OrderItem, Integer>{

}
