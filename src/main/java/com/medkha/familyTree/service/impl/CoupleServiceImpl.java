package com.medkha.familyTree.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medkha.familyTree.entity.Couple;
import com.medkha.familyTree.repository.CoupleRepository;
import com.medkha.familyTree.service.CoupleService;


@Service
public class CoupleServiceImpl implements CoupleService{
	
	@Autowired
	private CoupleRepository coupleRepository; 

	@Override
	public Couple createCouple(Couple couple) throws Exception{
		if(couple.getId() == null) {			
			return coupleRepository.save(couple);
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
