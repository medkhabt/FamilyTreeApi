package com.medkha.familyTree.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Family {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	private String name; 
	private String description; 
	
	private List<Person> familyMembers; 
	

}
