package com.medkha.familyTree.repository;

import org.springframework.stereotype.Repository;

import com.medkha.familyTree.entity.composite.CoupleComposite;

@Repository
public interface PersonRepository extends CoupleCompositeRepository, CustomPersonRepository{
	@SuppressWarnings("unchecked")
	@Override
	default CoupleComposite save(CoupleComposite person) { 
		return saveSafely(person.getParentsChild()); 
	}
	
	@Override
	default void deleteById(Long id) {
		deleteByIdSafely(id);
	}
}
