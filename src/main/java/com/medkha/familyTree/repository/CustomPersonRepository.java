package com.medkha.familyTree.repository;

import com.medkha.familyTree.entity.Person;

public interface CustomPersonRepository {
	
	Person saveSafely(Person entity); 
//	void removeCouple(Couple couple); 
	void deleteByIdSafely(Long entityId); 
}
