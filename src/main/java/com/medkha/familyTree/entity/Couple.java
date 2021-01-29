package com.medkha.familyTree.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.medkha.familyTree.entity.composite.CoupleComposite;


@Entity
@PrimaryKeyJoinColumn(name = "COUPLE_ID")
public class Couple extends CoupleComposite{
	
	@Transient
	private Set<CoupleComposite> children;
	@Transient
	private CoupleComposite parentsChild; // theChildOfTheParent
	/**
	 * TODO maybe keep track of divorced couples in further versions.
	 */
	@Transient
	private Set<CoupleComposite> partners; 
	
	
	public Couple(CoupleComposite aParentCouple, CoupleComposite parentsChild, Set<CoupleComposite> partners) {
		super();
		this.parentCouple = aParentCouple;
		this.parentsChild = parentsChild;
		this.partners = partners;
	}
	
	public Couple(CoupleComposite parentsChild, Set<CoupleComposite> partners) {
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

	public void setParentsChild(CoupleComposite parentsChild) {
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
	public CoupleComposite getParentsChild() {
		return this.parentsChild;
	}





	
	
	
	
	
}
