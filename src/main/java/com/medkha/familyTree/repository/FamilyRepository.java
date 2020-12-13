package com.medkha.familyTree.repository;

import org.springframework.data.repository.CrudRepository;

import com.medkha.familyTree.entity.Family;

public interface FamilyRepository extends 
			CrudRepository<Family, Long>{

	public Family findByName(String name); 
}
