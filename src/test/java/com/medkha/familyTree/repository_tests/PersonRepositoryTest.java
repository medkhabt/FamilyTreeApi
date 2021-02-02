package com.medkha.familyTree.repository_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void reset() {
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
	
	@Test
	@Transactional
	public void should_RetValidParents_When_ChildIsCreated() {
		// given
		
		Couple grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild()); 
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		grandCouple = coupleRepo.save(grandCouple); 
		
		CoupleComposite medReda = new Person(
				"Mohamed Reda", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(2001, 04, 02),
						"Agadir"
				),
				grandCouple
			); 
		// when 
		
		medReda = personRepo.save(medReda); 
		
		// then
		
		assertEquals(grandCouple,medReda.getParentCouple()); 
	}
	
	@Test
	@Transactional
	void shouldRetValidName_When_ModifyName() { 
		// given 
		
		// when
		
		this.grandFather.getParentsChild().setFirstName("Mohamed");
		this.grandFather = personRepo.save(this.grandFather); 
		
		
		assertTrue(
				personRepo
					.findById(this.grandFather.getId()).get()
						.getParentsChild()
							.getFirstName().equals("Mohamed")); 
	}
	
	@Test
	@Transactional
	void shouldBeEmpty_When_DeleteTheOnlyCouple() {
		// given 
		
		Couple grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild()); 
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		final Couple newGrandCouple = coupleRepo.save(grandCouple); 
		
		
		
		

		
		
		// when 

		
		coupleRepo.deleteById(grandCouple.getId());
	
		for(Couple couple : this.grandFather.getParentsChild().getActualCouplesEngagedIn()) {
			if(couple.equals(newGrandCouple)) {
				this.grandFather.getParentsChild().getActualCouplesEngagedIn().remove(couple);
				this.grandMother.getParentsChild().getActualCouplesEngagedIn().remove(couple);
			}
		}
		
//		personRepo.removeCouple(newGrandCouple);
		
		
		// then 
		
		assertTrue(personRepo.findById(this.grandFather.getId()).get().getParentsChild().getActualCouplesEngagedIn().isEmpty()); 
		assertNull(coupleRepo.findById(newGrandCouple.getId()).orElse(null)); 
	}
	
	@Test
	@Transactional
	public void shouldRetNull_When_PersonDeleted() {
		// given 
		Couple grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild()); 
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		grandCouple = coupleRepo.save(grandCouple); 
		
		// when 
		
		personRepo.deleteById(this.grandFather.getId());
		
		// then
		
		assertNull(personRepo.findById(this.grandFather.getId()).orElse(null)); 
		assertNull(coupleRepo.findById(grandCouple.getId()).orElse(null)); 
		assertEquals(0, personRepo.findById(this.grandMother.getId())
									.get()
										.getParentsChild()
											.getActualCouplesEngagedIn()
												.size());
		
		
		
	}
	 
	
}
