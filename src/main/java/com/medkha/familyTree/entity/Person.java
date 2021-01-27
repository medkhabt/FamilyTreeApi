package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.medkha.familyTree.entity.composite.ICoupleComposite;

import lombok.Data;

@Data

@Entity
public class Person implements ICoupleComposite{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	private String firstName; 
	private String lastName; 
	private Gender gender;
	private LocalDate birthdate; 
	private String birthplace; 
	private LocalDate deathDate; 
	private String deathPlace; 
	
	private String spacing; 
	
	private ICoupleComposite root; 

	public Person() {
		 
	}

	

	public Person(Long id, String firstName, String lastName, Gender gender, LocalDate birthdate, String birthplace,
			LocalDate deathDate, String deathPlace, String spacing, ICoupleComposite root) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.deathDate = deathDate;
		this.deathPlace = deathPlace;
		this.spacing = spacing;
		this.root = root;
	}

	public Person(Long i, String string) {
		this.id = i; 
		this.firstName = string; 
		this.lastName = string; 
	}

	@Override
	public ICoupleComposite getRealChild() {
		return this;
	}

	

	@Override
	public ICoupleComposite getParents() {
		return this.root;
	}

	@Override
	public void setParents(ICoupleComposite root) {
		this.root = root; 
	}
	
	

	@Override
	public String show() {
		return toString();
	}


	/**
	 * i will use the id as a comparator for now. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}







		
	
	
}
