package com.medkha.familyTree.service;

import java.util.Set;

import com.medkha.familyTree.entity.composite.CoupleComposite;

public interface PersonService {
	public CoupleComposite createPerson(CoupleComposite person); 
	public CoupleComposite updatePerson(CoupleComposite person); 
	public void deletePerson(Long id); 
	public void deleteAllPerson(); 
	
	public Set<CoupleComposite> findAllPerson();
	public CoupleComposite findPersonById(Long id);
	public Set<CoupleComposite> findPersonByName(String firstName, String lastName); 
	
}
