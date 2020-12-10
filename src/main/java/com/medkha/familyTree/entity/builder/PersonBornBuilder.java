package com.medkha.familyTree.entity.builder;

import java.util.Date;

public class PersonBornBuilder extends PersonBuilder {
	
	public PersonBornBuilder withBirthdate(Date birthdate) {
		person.setBirthdate(birthdate);;
		return this;
	}
	
	public PersonBornBuilder withBirthplace(String birthplace) {
		person.setBirthplace(birthplace);;
		return this;
	}
}
