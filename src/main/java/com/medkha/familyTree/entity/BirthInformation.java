package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BirthInformation {
	
	@Column(nullable = false)
	private LocalDate birthdate;
	@Column(nullable = false, length = 255)
	private String birthplace;
	
	protected BirthInformation() { 
		
	}
	
	public BirthInformation(LocalDate birthdate, String birthplace) {
		this.birthdate = birthdate; 
		this.birthplace = birthplace; 
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	
	
}
