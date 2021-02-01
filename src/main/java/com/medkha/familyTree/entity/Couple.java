package com.medkha.familyTree.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import com.medkha.familyTree.entity.composite.CoupleComposite;


@Entity
@PrimaryKeyJoinColumn(name = "COUPLE_ID")
public class Couple extends CoupleComposite{
	
	@OneToMany(mappedBy = "parentCouple",
			   fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<@Valid @NotNull CoupleComposite> children = new HashSet<>();
	@OneToOne(
			fetch = FetchType.LAZY, 
			optional = false , // if optional = true, as we have unique column ( we can have null for only one instance )  
			cascade = CascadeType.PERSIST
			)
	@Valid
	private Person parentsChild; // theChildOfTheParent
	/**
	 * TODO maybe keep track of divorced couples in further versions.
	 *  - One couple has One partnership 
	 *  - One Person can have multiple couples ( partners aka polygamy) 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
			name = "COUPLE_PERSON",
			joinColumns = 
				@JoinColumn(name = "COUPLE_ID"),
			inverseJoinColumns = 
				@JoinColumn(name = "PERSON_ID", nullable = false)
			)
	@Valid
	private Person partner; 
	
	protected Couple() {}
	
	public Couple(CoupleComposite aParentCouple, Person parentsChild, Person partner) {
		super();
		this.parentCouple = aParentCouple;
		this.parentsChild = parentsChild;
		this.partner = partner;
	}
	
	public Couple(Person parentsChild, Person partner) {
		super();
		this.parentCouple = null; 
		this.parentsChild = parentsChild;
		this.partner = partner;
	}
	
	public Set<CoupleComposite> getChildren() {
		return children;
	}

	public void setChildren(Set<CoupleComposite> children) {
		this.children = children;
	}

	public CoupleComposite getParentCouple() {
		return parentCouple;
	}

	public void setParentCouple(CoupleComposite parentCouple) {
		this.parentCouple = parentCouple;
	}

	public Person getPartner() {
		return partner;
	}

	public void setPartner(Person partner) {
		this.partner = partner;
	}

	public void setParentsChild(Person parentsChild) {
		this.parentsChild = parentsChild;
	}

	public void addChild(CoupleComposite child) {
		this.children.add(child);
	}
	
	public void removeChild(CoupleComposite child) {
		this.children.remove(child);
	}

	


	@Override
	public String show() {
		String msgBoxWithChildren = this.getSpacing(); 
		if(!this.children.isEmpty()) { 
			for(CoupleComposite child: children) {
				child.setSpacing(this.getSpacing() + "\t");
				msgBoxWithChildren += "\n"+ child.getSpacing()+ child.show();
				
			}
		}
		return toString() + msgBoxWithChildren ;
	}
	
	

	@Override
	public String toString() {
		return "Couple [parentsChild=" + parentsChild.getFirstName() + " " + parentsChild.getLastName() 
		+ ", partner=" + partner.getFirstName() + " " + partner.getLastName() + "]";
	}

	@Override
	public Person getParentsChild() {
		return this.parentsChild;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((parentsChild == null) ? 0 : parentsChild.hashCode());
		result = prime * result + ((partner == null) ? 0 : partner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple other = (Couple) obj;
		if (parentsChild == null) {
			if (other.parentsChild != null)
				return false;
		} else if (!parentsChild.equals(other.parentsChild))
			return false;
		if (partner == null) {
			if (other.partner != null)
				return false;
		} else if (!partner.equals(other.partner))
			return false;
		return true;
	}





	
	
	
	
	
}
