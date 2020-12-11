package com.medkha.familyTree.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;


public interface PersonRepository extends 
	PagingAndSortingRepository<Person, Long>{
		public Iterable<Person> findByFirstName(String firstName); 
		public Iterable<Person> findByBirthdate(Date birthdate);
		public Iterable<Person> findByBirthdateBefore(Date birthdate);
		public Iterable<Person> findByBirthdateAfter(Date birthdate);
		public Iterable<Person> findByBirthdateBetween(Date dateStart, Date dateEnd);
		public Iterable<Person> findByBirthplace(String birthplace); 
		public Iterable<Person> findByGender(Gender gender); 
		public Iterable<Person> findByLastName(String lastName);
		public Iterable<Person> findByDeathDateBefore(Date birthdate);
		public Iterable<Person> findByDeathDateAfter(Date birthdate);
		public Iterable<Person> findByDeathDateBetween(Date dateStart, Date dateEnd);
		public Iterable<Person> findByDeathPlace(String deathPlace); 
		

		
		/**
		 * NEEDS A TEST
		 * @param family
		 * @return
		 */
		@Query(value = "select f.familyMembers FROM Family as f where f.id=:idFamily")
		public Iterable<Person> getDemandeurByFamily(@Param("idFamily") long id); 
}
