package com.medkha.familyTree.entity.builder;


import com.medkha.familyTree.entity.Person;

import lombok.Data;

@Data
public class PersonBuilder {
	protected Person person = new Person(); 
	
	public PersonGeneralInfoBuilder has() {
		return new PersonGeneralInfoBuilder(); 
	}
	public PersonDeceasedBuilder deceased() {
		return new PersonDeceasedBuilder();  
	}
	public PersonBornBuilder born() { 
		return new PersonBornBuilder(); 
	}
	
	public PersonBuilder hasChild(Person child, Person partner) { 
		person
			.getChildren()
				.add(child); 
		return this; 
	}
	
	public PersonBuilder hasPartner(Person partner) {
		person
			.getPartners()
				.add(partner);
		return this; 
	}
	
	public PersonBuilder hasParent(Person parent) {
		person
			.getParents()
				.add(parent); 
		return this; 
	}
	
	
	
	
	
	
}
