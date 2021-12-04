package com.medkha.familyTree.dto.person_decorator.impl;

import com.medkha.familyTree.dto.DeathInformation;
import com.medkha.familyTree.dto.Person;
import com.medkha.familyTree.dto.person_decorator.PersonDecorator;

public class DeadPerson extends PersonDecorator {
    private DeathInformation deathInformation;
    public DeadPerson(Person person, DeathInformation deathInformation) {
        super(person);
        this.deathInformation = deathInformation;
    }

    public DeathInformation getDeathInformation() {
        return deathInformation;
    }

    public void setDeathInformation(DeathInformation deathInformation) {
        this.deathInformation = deathInformation;
    }
}
