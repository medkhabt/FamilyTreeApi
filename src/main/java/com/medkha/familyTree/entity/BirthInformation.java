package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;


@Embeddable
public class BirthInformation {
	
	@Column(nullable = false)
	@NotNull
	@PastOrPresent
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthInformation other = (BirthInformation) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;
		return true;
	}
	
	
}
