package com.medkha.familyTree.entity.builder;

import java.time.LocalDate;
import java.util.Date;

import com.medkha.familyTree.entity.Person;

public class PersonBornBuilder extends PersonBuilder {
	
	
	public PersonBornBuilder(Person person) {
		this.person = person; 
	}
	public PersonBornBuilder withBirthdate(LocalDate birthdate) {
		person.setBirthdate(birthdate);;
		return this;
	}
	
	public PersonBornBuilder withBirthplace(String birthplace) {
		person.setBirthplace(birthplace);;
		return this;
	}
}
