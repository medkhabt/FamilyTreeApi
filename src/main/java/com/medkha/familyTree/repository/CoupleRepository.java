package com.medkha.familyTree.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medkha.familyTree.entity.Couple;

@Repository
public interface CoupleRepository extends CrudRepository<Couple,Long>{

}
