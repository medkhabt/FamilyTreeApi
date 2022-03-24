package com.medkha.familyTree.service;

import java.time.LocalDate;
import java.util.Optional;

import com.medkha.familyTree.dto.DeathInformation;
import com.medkha.familyTree.dto.Marriage;
import com.medkha.familyTree.dto.Person;

public interface FamilyActionsService {
    public Marriage marriage(Person person1, Person person2, Optional<LocalDate> dateOfMarriage, Optional<LocalDate> dateEndOfMarriage);
    public Person death(Person deadPerson, DeathInformation deathInformation);
}
