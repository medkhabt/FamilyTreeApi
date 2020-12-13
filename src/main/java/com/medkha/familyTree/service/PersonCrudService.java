package com.medkha.familyTree.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonCrudService {
	@Autowired
	private PersonRepository personRepo ; 
	
	public Person createPerson(Person person) {
		return personRepo.save(person); 
	}
	
	public Person getPersonById(long id) throws Exception{ 
		return personRepo.findById(id).get(); 
	}
	

	public Set<Person> getAll() throws Exception{ 
		Set<Person> people = new HashSet<>(); 
		personRepo.findAll().forEach(people::add); 
		return people; 
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void addParent(long idPerson, long idParent) throws Exception{
		
		if(!hasParent(idPerson, idParent)) { 
			Person person = personRepo.findById(idPerson).get();
			Person parent = personRepo.findById(idParent).get();
			
			person.getParents().add(parent); 
			parent.getChildren().add(person); 
			
			personRepo.save(person);
			personRepo.save(parent); 
		}
		
	}
	
	/**
	 * Repetition ? : addChild and addParent are the same
	 * it seems like a duplicated code, but it is more 
	 * descriptive if we make to methods one to adda child
	 * and the other to add a parent, althought their doing
	 * the same job. 
	 * 
	 * @param idPerson
	 * @param idChild
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addChild(long idPerson, long idChild) throws Exception {
		if(!hasParent(idChild, idPerson)) {
			Person person = personRepo.findById(idPerson).get(); 
			Person child = personRepo.findById(idChild).get();
			
			child.getParents().add(person); 
			person.getChildren().add(child); 
			
			personRepo.save(person); 
			personRepo.save(child);
		}
		
		
	}
	/**
	 * TODO test this method.  
	 * 			+ LOGGER : if the id exists => update if not => create
	 * 			+ prototype design pattern ? 
	 * @param person : to be updated. 
	 * @return
	 */
	public Person updatePerson(Person person) {
//		if(personRepo.findById(person.getId()).get() != null) {
		return personRepo.save(person); 
//		}
	}
		
	public void deletePersonById(long id) throws Exception{
		Person personToDelete = personRepo.findById(id).get(); 
		 personRepo.delete(personToDelete);
	}
	
	
	
	
	/**
	 * 
	 * @param personId
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Boolean hasParent(long personId, long parentId) throws Exception{ 
		Person person = personRepo.findById(personId).get(); 
		return ( 
				( person.getParents().stream()
												.map(p -> p.getId())
												.filter(p -> p == parentId)
												.reduce((first, second)-> first).orElse(null)) 
				!= null ) ? true : false ;
	}
	
}
