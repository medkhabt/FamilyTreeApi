package com.medkha.familyTree.dto.person_decorator;

import com.medkha.familyTree.dto.Person;

public abstract class PersonDecorator extends Person {
    protected Person person;

    public PersonDecorator(Person person) {
        super();
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
