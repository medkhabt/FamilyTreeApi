package com.medkha.familyTree.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Family {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	private String name; 
	private String description; 
	
	@ManyToMany
	private Set<Person> familyMembers = new HashSet<>(); 
	

}
