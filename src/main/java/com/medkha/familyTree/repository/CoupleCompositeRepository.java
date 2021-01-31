package com.medkha.familyTree.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.medkha.familyTree.entity.composite.CoupleComposite;

@NoRepositoryBean
public interface CoupleCompositeRepository extends CrudRepository<CoupleComposite, Long>{
	
}
