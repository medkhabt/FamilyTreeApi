package com.medkha.familyTree.dto;

import java.time.LocalDate;


public class DeathInformation {


	private LocalDate deathDate;
	private String deathPlace;
	
	
	protected DeathInformation() {
		
	}


	public DeathInformation(LocalDate deathDate, String deathPlace) {
		this.deathDate = deathDate;
		this.deathPlace = deathPlace;
	}


	public LocalDate getDeathDate() {
		return deathDate;
	}


	public void setDeathDate(LocalDate deathDate) {
		this.deathDate = deathDate;
	}


	public String getDeathPlace() {
		return deathPlace;
	}


	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}
	
	
}
