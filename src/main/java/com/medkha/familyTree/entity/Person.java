package com.medkha.familyTree.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	private String firstName; 
	private String lastName; 
	private Gender gender;
	private LocalDate birthdate; 
	private String birthplace; 
	private LocalDate deathDate; 
	private String deathPlace; 
	
	@ManyToMany
	private Set<Person> parents = new HashSet<>();
	@ManyToMany
	private Set<Person> partners = new HashSet<>(); 
	@ManyToMany
	private Set<Person> children = new HashSet<>(); 
	@ManyToMany(mappedBy = "familyMembers")
	private Set<Family> families = new HashSet<>(); 
	
//	public void addChild(Person child) {
//		children.add(child); 
//	}
//	
//	public void addPartner(Person partner) { 
//		partners.add(partner);
//	}
//	
//	public void addParent(Person parent) {
//		parents.add(parent);
//	}
}
