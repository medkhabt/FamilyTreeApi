//package com.medkha.familyTree.service_tests;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.medkha.familyTree.entity.Gender;
//import com.medkha.familyTree.entity.Person;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class PersonCrudServiceTest {
//
//	@Autowired
////	PersonCrudService pcs;
//	
//	
//	
//	@Test
//	public void should_RetFalse_When_Check_if_MedKha_hasParent () {
//		 
//		
//		try {
//			//given
//			long personId = 1; 
//			long parentId = 2;
//			
//			//when
//			
//			Boolean isParent = pcs.hasParent(personId, parentId); 
//			
//			//then
//			assertTrue(isParent);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//	}
//	
//	@Disabled
//	@Test
//	public void should_RetTrue_When_Medkha_addParent_Fatema() {
//		try {
//			
//			//given 
//			long personId = 1; 
//			long parentId = 3;
//
//		       
//		    //when
//		       
//		       pcs.addParent(personId, parentId);
//		       Boolean isParent = pcs.hasParent(personId, parentId);
//		       
//		    //then
//		       
//		       assertTrue(isParent);
////			pcs.createPerson(); 
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
