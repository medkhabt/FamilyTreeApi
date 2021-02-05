package com.medkha.familyTree.service_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

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
import com.medkha.familyTree.service.CoupleService;
import com.medkha.familyTree.service.PersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CoupleServiceTest {
	
	private CoupleComposite grandpa; 
	private CoupleComposite grandma; 

	@Autowired
	CoupleService coupleService;
	@Autowired
	PersonService personService;  
	
	@BeforeEach
	public void init() {
		coupleService.deleteAllCouples(); 
		personService.deleteAllPerson();
		
		this.grandpa = new Person(
				"grandFather", 
				"LOUKHNATI", 
				Gender.MALE, 
				new BirthInformation(
						LocalDate.of(1928, 01, 10),
						"Oujda"
				),
				null
			); 
		
		 this.grandma = new Person(
				"grandMother", 
				"ITANKHOUL", 
				Gender.FEMALE, 
				new BirthInformation(
						LocalDate.of(1936, 11, 01),
						"Fes"
				),
				null
			);
		
	}
	
	@Test
	public void shouldReturnNotNull_When_CreartingNewCouple() {
		try {
		// given
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
		
		// when
			Couple result = coupleService.createCouple(grandCouple);
			
		// then 
			assertNotNull(coupleService.findCoupleById(result.getId()));
		
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
	@Test
	public void shouldThrowException_WhenCreatingExistingCouple() { 
		try {
			
			// given
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			// when
			coupleService.createCouple(grandCouple); 
			
			// then 
			fail("Didn't throw an Exception");
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	
	@Test 
	public void shouldReturnUpdatedCouple_When_UpdateCouple() {
		try {
			// given 
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			// when 
			grandCouple.setParentsChild(this.grandma.getParentsChild());
			coupleService.updateCouple(grandCouple); 
			
			// then 
			assertEquals(
					this.grandma.getId(), 
					coupleService
						.findCoupleById(grandCouple.getId())
							.getParentsChild()
								.getId()); 
		} catch (Exception e) {
			
		}
	}
	
	@Test 
	public void shouldThrowException_When_UpdateTransientCouple() { 
		try {
			// given
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			
			// when 
			coupleService.updateCouple(grandCouple); 
			
			// then
			
			fail("Didn't throw an Exception"); 
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	@Test 
	public void shouldRetNull_When_DeleteCouple() {
		try {
			// given 
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			// when 
			coupleService.deleteCouple(grandCouple.getId());
			
			// then
			assertNull(coupleService.findCoupleById(grandCouple.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	
	@Test
	public void shouldThrowException_When_DeleteWithNullId() {
		try {
			// given 
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			// when
			coupleService.deleteCouple(null);
			
			// then 
			fail("Doesn't throw new Exception when id = null"); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	@Test 
	public void shouldThrowException_When_DeleteWithNonExistingId() { 
		try {
			// given 
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			// when
			Long nonExistingId = (long) 2000; 
			nonExistingId = (nonExistingId.equals(grandCouple.getId())) ? nonExistingId + 1 : nonExistingId;  
			coupleService.deleteCouple(nonExistingId);
			
			// then 
			fail("Doesn't throw new Exception when no istance is found for that id."); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	@Test 
	public void shouldRetCorrectSize_When_SearchingForAllCouples() {
		try {
			// given 
			Couple grandCouple = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple = coupleService.createCouple(grandCouple);
			
			Couple grandCouple1 = new Couple(this.grandpa.getParentsChild(), this.grandma.getParentsChild());
			grandCouple1 = coupleService.createCouple(grandCouple1);
			
			// when
			int size = coupleService.findAllCouples().size(); 
			
			// then 
			assertEquals(2, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
