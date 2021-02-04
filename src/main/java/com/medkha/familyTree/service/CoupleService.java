package com.medkha.familyTree.service;

import java.util.Set;

import com.medkha.familyTree.entity.Couple;

public interface CoupleService {
	public Couple createCouple(Couple couple); 
	public Couple updateCouple(Couple couple); 
	public void deleteCouple(Long id); 
	public Set<Couple> findAllCouples(); 
	public Couple findCoupleById(Long id); 

}
