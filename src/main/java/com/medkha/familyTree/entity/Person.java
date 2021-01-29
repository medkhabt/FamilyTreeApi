package com.medkha.familyTree.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import com.medkha.familyTree.entity.composite.CoupleComposite;



@Entity
@PrimaryKeyJoinColumn(name = "PERSON_ID")
public class Person extends CoupleComposite{
	
	@NotNull
	private String firstName; 
	@NotNull
	private String lastName; 
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private BirthInformation birthInformation; 
	private DeathInformation deathInformation; 

	
	
	
	

	public Person() {
		 super(); 
	}
	

	public Person(String firstName, String lastName, Gender gender, BirthInformation birthInformation,
					CoupleComposite aParentCouple) {
		super(); 
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthInformation = birthInformation; 
		this.parentCouple = aParentCouple;
	}
	
	public Person(String firstName, String lastName, Gender gender, BirthInformation birthInformation,
			DeathInformation deathInformation, CoupleComposite aParentCouple) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthInformation = birthInformation; 
		this.deathInformation = deathInformation; 
		this.parentCouple = aParentCouple;
	}

	public Person(String string) {
		this.firstName = string; 
		this.lastName = string; 
	}


	

	
	

	@Override
	public String show() {
		return toString();
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public BirthInformation getBirthInformation() {
		return birthInformation;
	}

	public void setBirthInformation(BirthInformation birthInformation) {
		this.birthInformation = birthInformation;
	}

	

	public DeathInformation getDeathInformation() {
		return deathInformation;
	}


	public void setDeathInformation(DeathInformation deathInformation) {
		this.deathInformation = deathInformation;
	}


	public CoupleComposite getParentCouple() {
		return this.parentCouple;
	}

	public void setParentCouple(CoupleComposite aParentCouple) {
		this.parentCouple = aParentCouple;
	}


	@Override
	public CoupleComposite getParentsChild() {
		return this;
	}







		
	
	
}
