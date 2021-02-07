package com.medkha.familyTree.repository;

import com.medkha.familyTree.entity.Couple;

public interface CustomCoupleRepository {
	public Couple saveSafely(Couple couple); 
}
