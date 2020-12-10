package com.medkha.familyTree.entity.builder;

import java.util.Date;



public class PersonDeceasedBuilder extends PersonBuilder{
	
	public PersonDeceasedBuilder() {
		super(); 
	}
	
	public PersonDeceasedBuilder date(Date date) {
		person.setDeathDate(date);
		return this;
	}
	
	public PersonDeceasedBuilder place(String place) {
		person.setDeathPlace(place);
		return this; 
	}

}
