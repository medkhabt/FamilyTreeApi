package com.medkha.familyTree.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Date birthdate; 
	private String birthplace; 
	private Date deathDate; 
	private String deathPlace; 
	
	private List<Person> parents;
	private List<Person> partners; 
	private List<Person> children; 

	private List<Family> families; 
	
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
