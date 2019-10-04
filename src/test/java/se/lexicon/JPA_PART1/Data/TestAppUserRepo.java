package se.lexicon.JPA_PART1.Data;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.JPA_PART1.Entity.AppUser;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TestAppUserRepo {

	@Autowired
	private AppUserRepo testObject;
	private AppUser testUser;
	
	@Before
	public void setUp() {
		AppUser user = new AppUser("Test", "Testsson", "Test@lexicon.se");
		testUser = testObject.save(user);
	}
	
	@Test
	public void test_save_user_created_success() {
		
		assertNotNull(testUser);
		assertTrue(testUser.getUserId() != 0);
		assertEquals("Test", testUser.getFirstName());
		assertEquals("Testsson", testUser.getLastName());
		assertEquals("Test@lexicon.se", testUser.getEmail());
	}
	
	@Test
	public void test_given_emailTest_should_return_optional_of_email() {
		
		String email = "test";
		
		Optional<AppUser> result = testObject.findByEmailStartsWithIgnoreCase(email);
		
		assertTrue(result.isPresent());
		assertEquals("Test@lexicon.se", result.get().getEmail());
		
	}
}
