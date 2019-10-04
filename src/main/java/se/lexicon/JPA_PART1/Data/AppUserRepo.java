package se.lexicon.JPA_PART1.Data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.JPA_PART1.Entity.AppUser;

public interface AppUserRepo extends CrudRepository<AppUser, Integer> {

	Optional<AppUser> findByEmailStartsWithIgnoreCase(String email);
	
}
