package com.medkha.familyTree.dto;

import java.time.LocalDate;
import java.util.Optional;


public class BirthInformation {
	
	private Optional<LocalDate> birthDate;
	private Optional<String> birthPlace;
	
	public BirthInformation(Optional<LocalDate> birthDate, Optional<String> birthPlace) {
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
	}

	public Optional<LocalDate> getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Optional<LocalDate> birthDate) {
		this.birthDate = birthDate;
	}

	public Optional<String> getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(Optional<String> birthPlace) {
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
