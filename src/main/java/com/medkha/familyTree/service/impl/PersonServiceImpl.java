package com.medkha.familyTree.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.entity.composite.CoupleComposite;
import com.medkha.familyTree.repository.PersonRepository;
import com.medkha.familyTree.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepo; 
	
	@Override
	public Person createPerson(CoupleComposite person) {
		return personRepo.save(person.getParentsChild());
	}

	@Override
	public CoupleComposite updatePerson(CoupleComposite person) {
		return personRepo.save(person.getParentsChild());
	}

	@Override
	public void deletePerson(Long id) {
		if(id != null) { 
			personRepo.deleteById(id);
		}
		
	}

	@Override
	public Set<CoupleComposite> findAllPerson() {
		Set<CoupleComposite> everyPerson = new HashSet<>(); 
		personRepo.findAll().forEach(everyPerson::add);
		return everyPerson;
	}

	@Override
	public CoupleComposite findPersonById(Long id) {
		return personRepo.findById(id).orElse(null);
	}

	@Override
	public Set<CoupleComposite> findPersonByName(String firstName, String lastName) {
		Set<CoupleComposite> everyPersonWithThatName = new HashSet<>(); 
		personRepo.findByFirstNameAndLastName(firstName, lastName).forEach(everyPersonWithThatName::add);
		return everyPersonWithThatName;
	}

	@Override
	public void deleteAllPerson() {
		personRepo.deleteAll();
	}

}
