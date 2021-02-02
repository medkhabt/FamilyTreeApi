package com.medkha.familyTree;


import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.medkha.familyTree.entity.BirthInformation;
import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.composite.CoupleComposite;
import com.medkha.familyTree.repository.CoupleRepository;
import com.medkha.familyTree.repository.PersonRepository;


@SpringBootApplication
public class FamilyTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTreeApplication.class, args);
	}
	
	@Bean
	
	  public CommandLineRunner dataLoader(PersonRepository personRepo,
			  							CoupleRepository coupleRepo) {
		
	    return new CommandLineRunner() {
	      @Override
	      @Transactional
	      public void run(String... args) throws Exception {
	    	  CoupleComposite grandFather = new Person(
	  				"grandFather", 
	  				"LOUKHNATI", 
	  				Gender.MALE, 
	  				new BirthInformation(
	  						LocalDate.of(1928, 01, 10),
	  						"Oujda"
	  				),
	  				null
	  			); 
	  		
	  		CoupleComposite grandMother  = new Person(
	  				"grandMother", 
	  				"ITANKHOUL", 
	  				Gender.FEMALE, 
	  				new BirthInformation(
	  						LocalDate.of(1936, 11, 01),
	  						"Fes"
	  				),
	  				null
	  			); 
	  		
	  		personRepo.save(grandFather); 
	  		personRepo.save(grandMother); 
	  		
	    	Couple grandCouple = new Couple(grandFather.getParentsChild(), grandMother.getParentsChild()); 
	  		grandFather.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
	  		grandMother.getParentsChild().getActualCouplesEngagedIn().add(grandCouple); 
	  		
	  		grandCouple = coupleRepo.save(grandCouple); 
	      }
	      
	     
		      
	      
	    };
	}

}
