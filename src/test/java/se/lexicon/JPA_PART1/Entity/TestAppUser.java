package se.lexicon.JPA_PART1.Entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert. *;

public class TestAppUser {

	private AppUser testObject;
	
	@Before
	public void setUp() {
		testObject = new AppUser(1, "Test", "Testsson", "test@lexicon.se");
	}
	
	@Test
	public void testObject_sucsess_created() {
		assertEquals(1, testObject.getUserId());
		assertEquals("Test", testObject.getFirstName());
		assertEquals("Testsson", testObject.getLastName());
		assertEquals("test@lexicon.se", testObject.getEmail());
	}
	
	@Test
	public void test_equals_and_hashcode_true() {
		AppUser copy = new AppUser(1, "Test", "Testsson", "test@lexicon.se");
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		
		assertTrue(
				toString.contains("1") &&
				toString.contains("Test") &&
				toString.contains("Testsson") &&
				toString.contains("test@lexicon.se")
				);
	}
}
