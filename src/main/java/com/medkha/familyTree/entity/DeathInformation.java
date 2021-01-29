package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeathInformation {

	@Column(nullable = true)
	private LocalDate deathDate;
	@Column(length = 255, nullable = true)
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
