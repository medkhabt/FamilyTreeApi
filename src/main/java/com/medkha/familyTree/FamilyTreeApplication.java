package com.medkha.familyTree;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.medkha.familyTree.entity.Family;
import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.builder.PersonBuilder;
import com.medkha.familyTree.repository.FamilyRepository;
import com.medkha.familyTree.repository.PersonRepository;

@SpringBootApplication
public class FamilyTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTreeApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner dataLoader(PersonRepository personRepo,
			  							FamilyRepository familyRepo) {
		PersonBuilder pb = new PersonBuilder(); 
		
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	    	// instantiating the first family 
	    	  
	    	Family loukhnati = new Family(); 
	    	loukhnati.setName("LOUKHNATI");
	    	loukhnati.setDescription("Loukhnati family");
	    	familyRepo.save(
	    			loukhnati
	    			);
	    	
	    	
	    	// instantiating the first person
	    	Person p1 = pb
				.has()
					.firstName("Med Khalil")
					.lastName("LOUKHNATI")
					.gender(Gender.MALE)
				.born()
					.withBirthdate(LocalDate.of(1998, Month.DECEMBER, 28))
					.withBirthplace("Agadir")
				.build();
	    	
	    	p1.getFamilies().add(loukhnati);
	        personRepo.save(p1);
	      }
	    };
	}

}
