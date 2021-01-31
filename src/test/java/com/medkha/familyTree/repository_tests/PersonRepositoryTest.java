package com.medkha.familyTree.repository_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.medkha.familyTree.entity.BirthInformation;
import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.composite.CoupleComposite;
import com.medkha.familyTree.repository.CoupleRepository;
import com.medkha.familyTree.repository.PersonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	PersonRepository personRepo; 
	
	@Autowired
	CoupleRepository coupleRepo; 
	
	
	private CoupleComposite grandFather; 
	private CoupleComposite grandMother; 
	
	
	@BeforeEach
	public void init() {
		 this.grandFather = new Person(
				"grandFather", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1928, 01, 10),
						"Oujda"
				),
				null
			); 
		
		this.grandMother  = new Person(
				"grandMother", 
				"ITANKHOUL", 
				Gender.FEMALE, 
				new BirthInformation(
						LocalDate.of(1936, 11, 01),
						"Fes"
				),
				null
			); 
		
		personRepo.save(this.grandFather); 
		personRepo.save(this.grandMother); 
		
	}
	
	@AfterEach
	public void breakdown() {
		personRepo.deleteAll();
		this.grandFather = null; 
		this.grandMother = null; 
	}
	@Test
	public void should_RetCorrectFirstName_When_PersonSaved() { 
		// given 
		// String firstName, String lastName, Gender gender, BirthInformation birthInformation,
//		CoupleComposite aParentCouple
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
		
		medkhalil = personRepo.save(medkhalil); 
		
		// then
		
		assertEquals("Mohamed Khalil", personRepo.findById(medkhalil.getId()).get().getParentsChild().getFirstName()); 
		
	}
	
	
	@Test
	@Transactional
	public void should_RetTrue_When_PersonIsInCouple() {
		
		// given 
		 
		Couple grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild());
		
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		// when 
		
		grandCouple = coupleRepo.save(grandCouple); 
		
		this.grandFather = personRepo.findById(this.grandFather.getId()).get(); 
		this.grandMother = personRepo.findById(this.grandMother.getId()).get();
		// then
		
		assertTrue(this.grandFather.getParentsChild().getActualCouplesEngagedIn().iterator().next().equals(grandCouple)); 
		
		
	}
	
	
	
	
}
