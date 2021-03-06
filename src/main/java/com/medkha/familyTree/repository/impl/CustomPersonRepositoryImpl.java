package com.medkha.familyTree.repository.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.repository.CustomPersonRepository;


public class CustomPersonRepositoryImpl implements CustomPersonRepository{

//	@Autowired
//	@Qualifier("personRepository")
//	private PersonRepository personRepository;

	@PersistenceContext
	private EntityManager entityManager; 
	
	@Override
	public Person saveSafely(Person entity) {
		if(entity.getId() == null ) { 
				this.entityManager.persist(entity);
				return entity; 
		}
		else {
			
			return this.entityManager.merge(entity); 
		}
		
	}

	@Override
	public void deleteByIdSafely(Long entityId) {
		Person personToDelete = entityManager.find(Person.class, entityId); 
		for(Couple couple : personToDelete.getActualCouplesEngagedIn()) {
			for(Person partner: couple.getPartners()) {
				if(!partner.equals(personToDelete)) {
					partner.getActualCouplesEngagedIn().remove(couple); 
					entityManager.merge(partner); 
				}
			}
			entityManager.remove(couple);
		}
	
		entityManager.remove(personToDelete);
	}

//	@Override
//	public void removeCouple(Couple coupleToDelete) {
//		for(Couple couple : coupleToDelete.getParentsChild().getActualCouplesEngagedIn()) {
//			if(couple.equals(coupleToDelete)) {
//				couple.getParentsChild().getActualCouplesEngagedIn().remove(couple);
//				couple.getPartner().getActualCouplesEngagedIn().remove(couple); 
//			}
//		}
//		entityManager.merge(coupleToDelete.getParentsChild()); 
//		entityManager.merge(coupleToDelete.getPartner()); 
//		entityManager.remove(coupleToDelete);
//		
//		
//	}

	
	
}
