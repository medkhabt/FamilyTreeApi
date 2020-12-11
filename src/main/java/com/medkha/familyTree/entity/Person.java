package com.medkha.familyTree.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<Person> parents = new ArrayList<>();
	@ManyToMany
	private List<Person> partners = new ArrayList<>(); 
	@ManyToMany
	private List<Person> children = new ArrayList<>(); 
	@ManyToMany(targetEntity=Family.class)
	private List<Family> families = new ArrayList<>(); 
	
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
