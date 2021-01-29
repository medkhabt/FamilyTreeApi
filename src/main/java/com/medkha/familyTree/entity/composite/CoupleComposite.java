package com.medkha.familyTree.entity.composite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.medkha.familyTree.Constants;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CoupleComposite implements ICoupleComposite{
	
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	Long id; 
	
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * that insertable = false, block CreationTimestamp from setting the createdOn Date value. 
	 */
	/**
	 * test it with Generated on insert.  
	 */
	@NotNull
	@Column(/* TODO test with this => insertable = false ,*/ updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private Date createdOn; 
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP) // TIMESTAMP IS BY DEFAULT IN HIBERNATE, @Temporal is required in jpa. 
	@Column(insertable = false, updatable = false)
	@org.hibernate.annotations.Generated(
			org.hibernate.annotations.GenerationTime.ALWAYS 
			)
	private Date lastModified;
	
	@Transient
	private String spacing;
	
	public abstract ICoupleComposite getParentsChild(); 
//	public Set<ICoupleComposite> getSiblings(); 
	public abstract ICoupleComposite getParents(); 
	public abstract void setParents(ICoupleComposite aParentCouple); 
	 
	
	public CoupleComposite() {
		
	}
	
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public Long getId() { 
		return this.id; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CoupleComposite other = (CoupleComposite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * informative purposes, i should delete them after.  ( loggers.. ) 
	 */
	public abstract String show(); 
	public  String getSpacing() {
		return this.spacing;
	}
	
	public void setSpacing(String spacing) {
		this.spacing = spacing; 
	}
}
