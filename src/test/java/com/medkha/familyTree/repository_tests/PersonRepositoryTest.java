package com.medkha.familyTree.repository_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.builder.PersonBuilder;
import com.medkha.familyTree.repository.PersonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	PersonRepository repo; 
	
	PersonBuilder pb = new PersonBuilder(); 
	
	
	
	/**
	 * Tested with one instance in the database. 
	 */
	@Test
	public void should_RetTrue_When_FirstNameEqualMedKhalil() {
		//given 
		
		
		String firstName = "Med Khalil"; 
		String result ; 
		
		//when 
		
		Iterator<Person> p2Iterator = repo.findByFirstName(firstName).iterator();  
		result = ((Person)p2Iterator.next()).getFirstName(); 
		
		//then
		assertAll(
			() -> assertTrue(result.equals(firstName)),
			() -> assertFalse(result.equals("MedKhalil"))
				); 
		
	 
		
	}
	
	
}
