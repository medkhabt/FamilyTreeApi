package com.medkha.familyTree.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.medkha.familyTree.entity.composite.CoupleComposite;


@Entity
@PrimaryKeyJoinColumn(name = "COUPLE_ID")
public class Couple extends CoupleComposite{
	
	@OneToMany(mappedBy = "parentCouple",
			   fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<CoupleComposite> children;
	@OneToOne(
			fetch = FetchType.LAZY, 
			optional = false , // if optional = true, as we have unique column ( we can have null for only one instance )  
			cascade = CascadeType.PERSIST
			)
	private Person parentsChild; // theChildOfTheParent
	/**
	 * TODO maybe keep track of divorced couples in further versions.
	 */
	@Transient /*ManyToMany*/
	private Set<CoupleComposite> partners; 
	
	
	public Couple(CoupleComposite aParentCouple, Person parentsChild, Set<CoupleComposite> partners) {
		super();
		this.parentCouple = aParentCouple;
		this.parentsChild = parentsChild;
		this.partners = partners;
	}
	
	public Couple(Person parentsChild, Set<CoupleComposite> partners) {
		super();
		this.parentCouple = new Person("root"); 
		this.parentsChild = parentsChild;
		this.partners = partners;
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

	public Set<CoupleComposite> getPartners() {
		return partners;
	}

	public void setPartners(Set<CoupleComposite> partners) {
		this.partners = partners;
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
	public Person getParentsChild() {
		return this.parentsChild;
	}





	
	
	
	
	
}
