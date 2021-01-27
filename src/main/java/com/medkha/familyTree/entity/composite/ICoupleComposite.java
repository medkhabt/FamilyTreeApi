package com.medkha.familyTree.entity.composite;

import java.util.Set;

public interface ICoupleComposite {
	public ICoupleComposite getRealChild(); 
//	public Set<ICoupleComposite> getSiblings(); 
	public ICoupleComposite getParents(); 
	public void setParents(ICoupleComposite root); 
	 
	public boolean equals(Object cc); 
	public int hashCode();
	
	/**
	 * informative purposes, i should delete them after.  ( loggers.. ) 
	 */
	public String show(); 
	public String getSpacing(); 
	public void setSpacing(String spacing);
}
