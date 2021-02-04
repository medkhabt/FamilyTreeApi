package com.medkha.familyTree.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.composite.CoupleComposite;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long>, CustomPersonRepository{
	@SuppressWarnings("unchecked")
	@Override
	public default Person save(Person person) { 
		return saveSafely(person.getParentsChild()); 
	}
	
	@Override
	public default void deleteById(Long id) {
		deleteByIdSafely(id);
	}
	
	public Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName); 
}
