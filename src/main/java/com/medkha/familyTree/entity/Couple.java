package com.medkha.familyTree.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.medkha.familyTree.Constants;
import com.medkha.familyTree.entity.composite.ICoupleComposite;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Couple implements ICoupleComposite{
	/**
	 * will have an issue with the person entity, as i think of it as 
	 * they are the same, but here to instances may have the same id.
	 * (considerate different)
	 */
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	private Long id; 
	
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
	
	@Temporal(TemporalType.TIMESTAMP) // TIMESTAMP IS BY DEFAULT IN HIBERNATE, @Temporal is required in jpa. 
	@Column(insertable = false, updatable = false)
	@org.hibernate.annotations.Generated(
			org.hibernate.annotations.GenerationTime.ALWAYS 
			)

	private Date lastModified; 
	
	private Set<ICoupleComposite> children;
	private ICoupleComposite root; 
	private ICoupleComposite realChild;
	/**
	 * TODO maybe keep track of divorced couples in further versions.
	 */
	private Set<ICoupleComposite> partners; 
	
	
	
	@Transient
	private String spacing;
	
	
	
	
	
	public Couple(ICoupleComposite root, ICoupleComposite realChild, Set<ICoupleComposite> partners) {
		super();
		this.root = root;
		this.realChild = realChild;
		this.partners = partners;
	}
	
	public Couple(ICoupleComposite realChild, Set<ICoupleComposite> partners) {
		super();
		this.root = new Person((long) 0,"root"); 
		this.realChild = realChild;
		this.partners = partners;
	}
	
	public void addChild(ICoupleComposite child) {
		this.children.add(child);
	}
	
	public void removeChild(ICoupleComposite child) {
		this.children.remove(child);
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
		String msgBoxWithChildren = spacing; 
		if(!this.children.isEmpty()) { 
			for(ICoupleComposite child: children) {
				child.setSpacing(spacing + "\t");
				msgBoxWithChildren += "\n"+ child.getSpacing()+ child.show();
				
			}
		}
		return toString() + msgBoxWithChildren ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple other = (Couple) obj;
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
