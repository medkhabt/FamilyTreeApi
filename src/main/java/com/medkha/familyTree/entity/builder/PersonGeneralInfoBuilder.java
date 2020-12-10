package com.medkha.familyTree.entity.builder;

import com.medkha.familyTree.entity.Gender;


public class PersonGeneralInfoBuilder extends PersonBuilder{
	
	public PersonGeneralInfoBuilder() {
		super(); 
	}
	public PersonGeneralInfoBuilder firstName(String firstName) { 
		person.setFirstName(firstName);
		return this; 
	}
	
	public PersonGeneralInfoBuilder lastName(String lastName) {
		person.setLastName(lastName);
		return this;
	}
	
	public PersonGeneralInfoBuilder gender(Gender gender) {
		person.setGender(gender);
		return this;
	}
}
