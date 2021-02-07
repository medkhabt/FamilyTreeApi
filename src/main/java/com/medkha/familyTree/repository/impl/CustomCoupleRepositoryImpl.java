package com.medkha.familyTree.repository.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.repository.CustomCoupleRepository;

public class CustomCoupleRepositoryImpl implements CustomCoupleRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	

	
	
	@Override

	public Couple saveSafely(Couple couple) {

		Set<Person> partnersUpdate = new HashSet<Person>(couple.getPartners()); 
		for(Person partner : partnersUpdate) {
			if(partner.getId() != null) { 
				Person mergedPartner = this.entityManager.find(Person.class, partner.getId()); 
				couple.getPartners().remove(partner); 
				couple.getPartners().add(mergedPartner);
			}
			else { 
				this.entityManager.persist(partner);
			}
		}
		
		couple.setPartners(partnersUpdate);
		
		if(couple.getId() == null) {
			
			this.entityManager.persist(couple);
			return couple; 
		}
		else { 
			return this.entityManager.merge(couple); 
		}
	}

}
