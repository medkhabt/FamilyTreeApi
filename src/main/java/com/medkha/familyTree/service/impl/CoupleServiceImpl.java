package com.medkha.familyTree.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.entity.Person;
import com.medkha.familyTree.repository.CoupleRepository;
import com.medkha.familyTree.repository.PersonRepository;
import com.medkha.familyTree.service.CoupleService;


@Service
public class CoupleServiceImpl implements CoupleService{
	
	@Autowired
	private CoupleRepository coupleRepository; 

	@Autowired 
	private PersonRepository personRepository; 
	
	@Override
	public Couple createCouple(Couple couple) throws Exception{
		if(couple.getId() == null) {	
			
			Person partners[] = couple.getPartners().stream().toArray(Person[] ::new); 
//			Iterator<Person> partnersInCoupleIterator = couple.getPartners().iterator(); 
//			Person firstPartner = partnersInCoupleIterator.next();
			if(partners[0].getId() !=null ) { 				
				partners[0] = personRepository.findById(partners[0].getId()).get(); 
				for(Couple coupleIn : partners[0].getActualCouplesEngagedIn()) { 
					for(Person partner : coupleIn.getPartners()) {
//						Person theOtherPartner = partnersInCoupleIterator.next(); 
						if(partner.getId().equals(partners[1].getId())) {
							throw new Exception("Couple aleary existing"); 
						}
					}
				}
			}
			
				
			couple.getPartners().forEach(
					(partner) -> partner.addActualCouple(couple)
					);
			return  coupleRepository.save(couple); 
		}
		else {
			throw new Exception("Couple: " + couple.toString() + " exist already!"); 
		}
	}

	@Override
	public Couple updateCouple(Couple couple) throws Exception {
		if(couple.getId() != null) {			
			return coupleRepository.save(couple);
		}
		else {
			throw new Exception("Couple: " + couple.toString() + " doesn't exist in the database. "); 
		}
	}

	@Override
	public void deleteCouple(Long id) throws Exception {
		if(id == null ) {
			throw new Exception("Null Id as a parameter in deleteCouple is not valid."); 
		}
		if(findCoupleById(id) == null) { 
			throw new Exception("Couple to delete doesn't exist in the database."); 
		}
		else { 			
			coupleRepository.deleteById(id);
		}
	}

	@Override
	public Set<Couple> findAllCouples() {
		Set<Couple> allCouples = new HashSet<>();
		coupleRepository.findAll().forEach(allCouples::add);
		return allCouples;
	}

	@Override
	public Couple findCoupleById(Long id) {
		return (Couple) coupleRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteAllCouples() {
		coupleRepository.deleteAll();
	}
	

}
