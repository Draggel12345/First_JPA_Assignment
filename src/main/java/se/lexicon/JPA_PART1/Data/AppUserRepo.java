package se.lexicon.JPA_PART1.Data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.AppUser;

public interface AppUserRepo extends CrudRepository<AppUser, Integer> {

}
