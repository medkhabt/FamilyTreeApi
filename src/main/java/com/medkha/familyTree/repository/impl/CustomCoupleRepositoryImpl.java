package com.medkha.familyTree.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.repository.CustomCoupleRepository;
import com.medkha.familyTree.repository.CustomPersonRepository;

public class CustomCoupleRepositoryImpl implements CustomCoupleRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	

	
	
	@Override
	@Transactional
	public Couple saveSafely(Couple couple) {
	
	
		for(Person partner : couple.getPartners()) {
			if(partner.getId() != null) { 
				this.entityManager.merge(partner); 
			}
			else { 
				this.entityManager.persist(partner);
			}
		}
		
		if(couple.getId() == null) {
			
			this.entityManager.persist(couple);
			return couple; 
		}
		else { 
			return this.entityManager.merge(couple); 
		}
	}

}
