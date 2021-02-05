package com.medkha.familyTree.service;

import java.util.Set;

import com.medkha.familyTree.entity.Couple;

public interface CoupleService {
	public Couple createCouple(Couple couple) throws Exception; 
	public Couple updateCouple(Couple couple) throws Exception; 
	public void deleteCouple(Long id) throws Exception; 
	public Set<Couple> findAllCouples(); 
	public Couple findCoupleById(Long id);
	public void deleteAllCouples(); 

}
