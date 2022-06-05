package com.medkha.familyTree.dto;

import java.time.LocalDate;
import java.util.Optional;


public class DeathInformation {


	private Optional<LocalDate> deathDate;
	private Optional<String> deathPlace;
	
	
	protected DeathInformation() {
		this.deathDate = Optional.empty();
		this.deathPlace = Optional.empty();
	}


	public DeathInformation(Optional<LocalDate> deathDate, Optional<String> deathPlace) {
		this.deathDate = deathDate;
		this.deathPlace = deathPlace;
	}

	public Optional<LocalDate> getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Optional<LocalDate> deathDate) {
		this.deathDate = deathDate;
	}

	public Optional<String> getDeathPlace() {
		return deathPlace;
	}

	public void setDeathPlace(Optional<String> deathPlace) {
		this.deathPlace = deathPlace;
	}
}
