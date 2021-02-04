package com.medkha.familyTree.service_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.medkha.familyTree.entity.BirthInformation;
import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.composite.CoupleComposite;
import com.medkha.familyTree.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
	PersonService personService;
	
	@BeforeEach
	public void init() {
		personService.deleteAllPerson(); 
	}
	
	@Test
	public void ReturnValidFirstName_When_CreatePerson() {
		// given 
		
		CoupleComposite medkhalil = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1998, 12, 28),
						"Agadir"
				),
				null
			); 
		
		// when 
		
		CoupleComposite result = personService.createPerson(medkhalil);
		
		// then
		
		assertEquals(medkhalil.getParentsChild().getFirstName(), personService.findPersonById(result.getId()).getParentsChild().getFirstName()); 
	}
	
	@Test
	public void ReturnValidFirstName_When_UpdatingPerson() {
		// given 
		
		CoupleComposite medkhalil = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1998, 12, 28),
						"Agadir"
				),
				null
			); 
		medkhalil = personService.createPerson(medkhalil);
		
		// when 
		
		medkhalil.getParentsChild().setFirstName("Med Khalil");
		
		CoupleComposite result = personService.updatePerson(medkhalil); 

		// then
		
		assertEquals(
					medkhalil.getParentsChild().getFirstName(), 
					personService.findPersonById(result.getId())
						.getParentsChild().getFirstName()
				); 	
		
	}
	
	@Test 
	public void RetValidNumberOfPersonInstances_When_RequestingTofindThem() { 
		// given 
		CoupleComposite medkhalil = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1998, 12, 28),
						"Agadir"
				),
				null
			); 
		
		CoupleComposite medreda = new Person(
				"Mohamed Reda", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(2001, 04, 02),
						"Agadir"
				),
				null
			);
		
		personService.createPerson(medkhalil);
		personService.createPerson(medreda);
		
		
		// when 
		int size = personService.findAllPerson().size(); 
		log.info("******** start of the find all person list ");
		personService.findAllPerson().forEach((person) -> log.info("id : " + person.getId() + "    " + person.toString()));
		log.info("******** end of the find all person list ");
		
		// then
		
		assertEquals(size, 2) ; 
		
	}
	
	@Test
	void shouldRetCorrectPerson_When_SearchingForPersonByName() {
		// given 
		CoupleComposite medkhalil = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1998, 12, 28),
						"Agadir"
				),
				null
			); 
		CoupleComposite medkhalil1 = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1999, 12, 28),
						"Agadir"
				),
				null
			); 
		
		CoupleComposite medreda = new Person(
				"Mohamed Reda", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(2001, 04, 02),
						"Agadir"
				),
				null
			);
		
		medkhalil = personService.createPerson(medkhalil);
		medkhalil1 = personService.createPerson(medkhalil1);
		medreda = personService.createPerson(medreda);
		
		// when 
		
		Set<CoupleComposite> resultSet = personService.findPersonByName("Mohamed Khalil", "LOUKHNATI"); 
		
		
		
		// then 
		
		assertTrue(resultSet.contains(medkhalil));
		assertTrue(resultSet.contains(medkhalil1));
		assertFalse(resultSet.contains(medreda));
		
	}
	
	@Test 
	void shouldRetNull_When_DeletePerson() {
		// given
		
		CoupleComposite medkhalil = new Person(
				"Mohamed Khalil", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1998, 12, 28),
						"Agadir"
				),
				null
			); 
		
		medkhalil = personService.createPerson(medkhalil); 
		
		
		// when
		
		personService.deletePerson(medkhalil.getId());
		
		// then 
		
		assertNull(personService.findPersonById(medkhalil.getId()));
	}
	

}
