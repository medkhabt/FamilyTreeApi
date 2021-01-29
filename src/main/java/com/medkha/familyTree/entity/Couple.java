package com.medkha.familyTree.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.medkha.familyTree.entity.composite.CoupleComposite;
import com.medkha.familyTree.entity.composite.ICoupleComposite;


@Entity
@PrimaryKeyJoinColumn(name = "COUPLE_ID")
public class Couple extends CoupleComposite{
	
	private Set<ICoupleComposite> children;
	private ICoupleComposite parentCouple; 
	private ICoupleComposite parentsChild; // theChildOfTheParent
	/**
	 * TODO maybe keep track of divorced couples in further versions.
	 */
	private Set<ICoupleComposite> partners; 
	
	
	public Couple(ICoupleComposite aParentCouple, ICoupleComposite parentsChild, Set<ICoupleComposite> partners) {
		super();
		this.parentCouple = aParentCouple;
		this.parentsChild = parentsChild;
		this.partners = partners;
	}
	
	public Couple(ICoupleComposite parentsChild, Set<ICoupleComposite> partners) {
		super();
		this.parentCouple = new Person("root"); 
		this.parentsChild = parentsChild;
		this.partners = partners;
	}
	
	public Set<ICoupleComposite> getChildren() {
		return children;
	}

	public void setChildren(Set<ICoupleComposite> children) {
		this.children = children;
	}

	public ICoupleComposite getParentCouple() {
		return parentCouple;
	}

	public void setParentCouple(ICoupleComposite parentCouple) {
		this.parentCouple = parentCouple;
	}

	public Set<ICoupleComposite> getPartners() {
		return partners;
	}

	public void setPartners(Set<ICoupleComposite> partners) {
		this.partners = partners;
	}

	public void setParentsChild(ICoupleComposite parentsChild) {
		this.parentsChild = parentsChild;
	}

	public void addChild(ICoupleComposite child) {
		this.children.add(child);
	}
	
	public void removeChild(ICoupleComposite child) {
		this.children.remove(child);
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
		String msgBoxWithChildren = this.getSpacing(); 
		if(!this.children.isEmpty()) { 
			for(ICoupleComposite child: children) {
				child.setSpacing(this.getSpacing() + "\t");
				msgBoxWithChildren += "\n"+ child.getSpacing()+ child.show();
				
			}
		}
		return toString() + msgBoxWithChildren ;
	}


	@Override
	public ICoupleComposite getParentsChild() {
		return this.parentsChild;
	}

	
	
	
	
	
}
