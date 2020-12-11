package com.medkha.familyTree.entity.builder;

import com.medkha.familyTree.entity.Gender;
import com.medkha.familyTree.entity.Person;


public class PersonGeneralInfoBuilder extends PersonBuilder{
	
	
	public PersonGeneralInfoBuilder(Person person) { 
		this.person = person; 
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
