package com.medkha.familyTree.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.medkha.familyTree.Constants;
import com.medkha.familyTree.entity.composite.ICoupleComposite;



@Entity
public class Person implements ICoupleComposite{
	
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id; 
	private String firstName; 
	private String lastName; 
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private BirthInformation birthInformation; 
	private DeathInformation deathInformation; 

	
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * that insertable = false, block CreationTimestamp from setting the createdOn Date value. 
	 */
	/**
	 * test it with Generated on insert.  
	 */
	@Column(/* TODO test with this => insertable = false ,*/ updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private Date createdOn; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	@org.hibernate.annotations.Generated(
			org.hibernate.annotations.GenerationTime.ALWAYS 
			)

	private Date lastModified; 
	
	@Transient
	private String spacing; 
	
	private ICoupleComposite parentCouple; 

	public Person() {
		 
	}
	

	public Person(Long id, String firstName, String lastName, Gender gender, BirthInformation birthInformation,
					String spacing, ICoupleComposite aParentCouple) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthInformation = birthInformation; 
		this.spacing = spacing;
		this.parentCouple = aParentCouple;
	}
	
	public Person(Long id, String firstName, String lastName, Gender gender, BirthInformation birthInformation,
			DeathInformation deathInformation,  String spacing, ICoupleComposite aParentCouple) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthInformation = birthInformation; 
		this.deathInformation = deathInformation; 
		this.spacing = spacing;
		this.parentCouple = aParentCouple;
	}

	public Person(Long i, String string) {
		this.id = i; 
		this.firstName = string; 
		this.lastName = string; 
	}


	

	@Override
	public ICoupleComposite getParents() {
		return this.getParentCouple();
	}

	@Override
	public void setParents(ICoupleComposite aParentCouple) {
		this.setParentCouple(aParentCouple);; 
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


	public BirthInformation getBirthInformation() {
		return birthInformation;
	}

	public void setBirthInformation(BirthInformation birthInformation) {
		this.birthInformation = birthInformation;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getLastModified() {
		return lastModified;
	}

	

	public DeathInformation getDeathInformation() {
		return deathInformation;
	}


	public void setDeathInformation(DeathInformation deathInformation) {
		this.deathInformation = deathInformation;
	}


	public ICoupleComposite getParentCouple() {
		return this.parentCouple;
	}

	public void setParentCouple(ICoupleComposite aParentCouple) {
		this.parentCouple = aParentCouple;
	}

	public Long getId() {
		return id;
	}


	@Override
	public ICoupleComposite getParentsChild() {
		return this;
	}







		
	
	
}
