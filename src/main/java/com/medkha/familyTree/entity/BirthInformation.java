package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BirthInformation {
	
	@Column(nullable = false)
	private LocalDate birthDate;
	@Column(nullable = false, length = 255)
	private String birthPlace;
	
	protected BirthInformation() { 
		
	}
	
	public BirthInformation(LocalDate birthDate, String birthPlace) {
		this.birthDate = birthDate; 
		this.birthPlace = birthPlace; 
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	
}
