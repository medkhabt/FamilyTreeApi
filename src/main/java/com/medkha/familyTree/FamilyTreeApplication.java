package com.medkha.familyTree;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FamilyTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyTreeApplication.class, args);
	}
	
//	@Bean
//	  public CommandLineRunner dataLoader(PersonRepository personRepo,
//			  							FamilyRepository familyRepo) {
//		PersonBuilder pb1 = new PersonBuilder();
//		PersonBuilder pb2 = new PersonBuilder(); 
//		
//	    return new CommandLineRunner() {
//	      @Override
//	      public void run(String... args) throws Exception {
//	    	// instantiating the first family 
//	    	  
////	    	Family loukhnati = new Family(); 
////	    	loukhnati.setName("LOUKHNATI");
////	    	loukhnati.setDescription("Loukhnati family");
////	    	familyRepo.save(
////	    			loukhnati
////	    			);
//	    	
//	    	
//	    	// instantiating the first person
//	    	Person p1 = pb1
//				.has()
//					.firstName("Med Khalil")
//					.lastName("LOUKHNATI")
//					.gender(Gender.MALE)
//				.born()
//					.withBirthdate(LocalDate.of(1998, Month.DECEMBER, 28))
//					.withBirthplace("Agadir")
//				.build();
//	    	
////	    	p1.getFamilies().add(loukhnati);
//	        personRepo.save(p1);
//	        
//	        //instantiatin the second person (first parent ) 
//		      Person p2 = pb2
//						.has()
//							.firstName("Bouchaib")
//							.lastName("LOUKHNATI")
//							.gender(Gender.MALE)
//						.born()
//							.withBirthdate(LocalDate.of(1962, Month.DECEMBER, 17))
//							.withBirthplace("Azemmour")
//						.build();
//			    	
////			    	p2.getFamilies().add(loukhnati);
//			        personRepo.save(p2);
//	      }
//	      
//	     
//		      
//	      
//	    };
//	}

}
