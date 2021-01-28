package com.medkha.familyTree.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.medkha.familyTree.entity.composite.ICoupleComposite;



@Entity
public class Person implements ICoupleComposite{
	
	@Id
	@GeneratedValue(generator = "'ID_GENERATOR")
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

	@Override
	public String getSpacing() {
		return this.spacing;
	}

	@Override
	public void setSpacing(String spacing) {
		this.spacing = spacing; 
		
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

	public ICoupleComposite getRoot() {
		return root;
	}

	public void setRoot(ICoupleComposite root) {
		this.root = root;
	}

	public Long getId() {
		return id;
	}







		
	
	
}
