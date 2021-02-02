package com.medkha.familyTree.repository_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CoupleRepositoryTest {
	
	@Autowired
	CoupleRepository coupleRepo;
	
	@Autowired 
	PersonRepository personRepo;
	
	private CoupleComposite grandFather; 
	private CoupleComposite grandMother; 
	
	private Couple grandCouple; 
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
		
		this.grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild());
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		// when
		
		this.grandCouple = coupleRepo.save(this.grandCouple);
		
	}
	
	@AfterEach
	public void reset() {
		this.grandFather = null; 
		this.grandMother = null; 
		this.grandCouple = null; 
	}
	
	@Test
	@Transactional
	public void shouldRetNotEmptyCouplesEngagedIn_When_CoupleIsCreated() { 
		// given
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
		
		this.grandCouple = new Couple(this.grandFather.getParentsChild(), this.grandMother.getParentsChild());
		this.grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		this.grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
		
		// when
		
		this.grandCouple = coupleRepo.save(this.grandCouple);
		
		// then 
		
		assertNotNull(this.grandCouple.getId()); 
		assertEquals(1, personRepo.findById(this.grandFather.getId()).get().getParentsChild().getActualCouplesEngagedIn().size()); ;
	
	
	}
	
	@Test
	@Transactional
	public void shouldRetCorrectParents_When_CoupleCreatedWithParents() {
		// given
				CoupleComposite father = new Person(
						"Father", 
						"LOUKHNATI", 
						Gender.MALE, 
						new BirthInformation(
								LocalDate.of(1968, 01, 10),
								"Oujda"
						),
						this.grandCouple
					); 
					
				CoupleComposite mother  = new Person(
						"Mother", 
						"ITANKHOUL", 
						Gender.FEMALE, 
						new BirthInformation(
								LocalDate.of(1976, 11, 01),
								"Fes"
						),
						this.grandCouple
					); 
				
				
				
				Couple couple = new Couple(this.grandCouple, father.getParentsChild(), mother.getParentsChild());
				mother.getParentsChild().getActualCouplesEngagedIn().add(couple); 
				father.getParentsChild().getActualCouplesEngagedIn().add(couple); 
				couple = coupleRepo.save(couple); 
				// when
				
				
				
				this.grandCouple.addChild(couple);
				
				log.info("(*********** number of children in the grandCouple" + this.grandCouple.getChildren().size());
				couple = this.coupleRepo.save(couple); 
				
				
				// then 
				assertEquals(1, ((Couple) this.coupleRepo.findById(this.grandCouple.getId()).get()).getChildren().size());
				assertEquals(
						this.grandCouple, 
						this.coupleRepo
								.findById(couple.getId()).get()
									.getParentCouple());
				
	}
	
	@Test
	@Transactional
	void shouldRetCorrectPersonChild_When_AddChildToCouple() {
		// given 
		
		CoupleComposite father = new Person(
				"Father", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1968, 01, 10),
						"Oujda"
				),
				this.grandCouple
			); 
		
		this.grandCouple.addChild(father);		
		
		// when 
		
		father = this.personRepo.save(father); 
		
		// then 
		
		assertEquals( 
				1, 
				((Couple)this.coupleRepo
								.findById(this.grandCouple.getId()).get())
									.getChildren().size()
				); 
	}
	
	@Test
	@Transactional
	public void shouldRetCorrectCouple_When_PersonIsMarried() {
		// given 
		CoupleComposite father = new Person(
				"Father", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1968, 01, 10),
						"Oujda"
				),
				this.grandCouple
			); 
		
		CoupleComposite mother  = new Person(
				"Mother", 
				"ITANKHOUL", 
				Gender.FEMALE, 
				new BirthInformation(
						LocalDate.of(1976, 11, 01),
						"Fes"
				),
				this.grandCouple
			); 
		
		this.grandCouple.addChild(father);		
		
		father = this.personRepo.save(father); 
		
		// when 
		
		Couple couple = new Couple(this.grandCouple, father.getParentsChild(), mother.getParentsChild());
		mother.getParentsChild().getActualCouplesEngagedIn().add(couple); 
		father.getParentsChild().getActualCouplesEngagedIn().add(couple); 
		
		this.grandCouple.addChild(couple);
		
		couple = coupleRepo.save(couple); 
		
		// then 
		
		assertEquals(
				2, 
				((Couple)this.coupleRepo
								.findById(this.grandCouple.getId()).get())
									.getChildren().size()); 
		
		
	}
	
	@Test
	@Transactional
	public void shouldRetNull_When_ChildCoupleDivorsed() {
		// given 
		
		CoupleComposite father = new Person(
				"Father", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1968, 01, 10),
						"Oujda"
				),
				this.grandCouple
			); 
			
		CoupleComposite mother  = new Person(
				"Mother", 
				"ITANKHOUL", 
				Gender.FEMALE, 
				new BirthInformation(
						LocalDate.of(1976, 11, 01),
						"Fes"
				),
				this.grandCouple
			); 
		
		
		
		Couple coupleToDelete = new Couple(this.grandCouple, father.getParentsChild(), mother.getParentsChild());
		mother.getParentsChild().getActualCouplesEngagedIn().add(coupleToDelete); 
		father.getParentsChild().getActualCouplesEngagedIn().add(coupleToDelete); 
		coupleToDelete = coupleRepo.save(coupleToDelete); 
		
		this.grandCouple.addChild(coupleToDelete);
		
		final Couple finalCoupleToDelete = this.coupleRepo.save(coupleToDelete); 
		
		// when 

		
		for(Couple couple : father.getParentsChild().getActualCouplesEngagedIn()) {
			if(couple.equals(finalCoupleToDelete)) {
				log.info("*************** inside the removal of the couple in the partners objects");
				father.getParentsChild().getActualCouplesEngagedIn().remove(couple);
				mother.getParentsChild().getActualCouplesEngagedIn().remove(couple);
			}
		}
		
		this.coupleRepo.deleteById(coupleToDelete.getId());
		
		this.grandCouple.removeChild(coupleToDelete);
		// then
		
		assertNull(this.coupleRepo.findById(coupleToDelete.getId()).orElse(null)); 
		assertEquals(
				0,
				((Couple)this.coupleRepo.findById(this.grandCouple.getId()).get())
									.getChildren().size());
		assertEquals(
				0,
				this.personRepo.findById(father.getId()).get()
									.getParentsChild()
										.getActualCouplesEngagedIn().size()
				);
		
	}
	
	
}
