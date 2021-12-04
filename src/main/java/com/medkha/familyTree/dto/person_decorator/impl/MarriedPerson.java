package com.medkha.familyTree.dto.person_decorator.impl;

import java.util.HashSet;
import java.util.Set;

import com.medkha.familyTree.dto.Person;
import com.medkha.familyTree.dto.person_decorator.PersonDecorator;

public class MarriedPerson extends PersonDecorator {
    private Set<Person> partners = new HashSet<>();
    public MarriedPerson(Person person, Person partner) {
        super(person);
        partners.add(partner);
    }
    public void addPartner(Person person) { this.partners.add(person);}
    public void removePartner(Person person) {
        this.partners.remove(person);
    }
    public Set<Person> getPartners(){
        return new HashSet<>(this.partners);
    }
}
