package com.medkha.familyTree.entity.builder;

import java.time.LocalDate;
import java.util.Date;

import com.medkha.familyTree.entity.Person;



public class PersonDeceasedBuilder extends PersonBuilder{
	
	public PersonDeceasedBuilder(Person person) {
		this.person = person;
	}
	
	public PersonDeceasedBuilder date(LocalDate date) {
		person.setDeathDate(date);
		return this;
	}
	
	public PersonDeceasedBuilder place(String place) {
		person.setDeathPlace(place);
		return this; 
	}

}
