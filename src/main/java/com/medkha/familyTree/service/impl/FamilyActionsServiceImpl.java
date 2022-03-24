package com.medkha.familyTree.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medkha.familyTree.dto.DeathInformation;
import com.medkha.familyTree.dto.Marriage;
import com.medkha.familyTree.dto.Person;
import com.medkha.familyTree.service.FamilyActionsService;

@Service
public class FamilyActionsServiceImpl implements FamilyActionsService {

    @Override
    public Marriage marriage(Person person1, Person person2, Optional<LocalDate> dateOfMarriage, Optional<LocalDate> dateEndOfMarriage) {
        Marriage marriageToCreate = new Marriage.MarriageBuilder()
                .addCouple(person1, person2)
                .addDateOfMarriage(dateOfMarriage)
                .addDateEndOfMarriage(dateEndOfMarriage)
                .build();

        return marriageToCreate;
    }

    @Override
    public Person death(Person deadPerson, DeathInformation deathInformation) {
        // get all the person's current marriages and update the end of marriage with the person's death date if it exists.
        Person deadPersonResult = new Person.PersonBuilder()
                            .addFirstName(deadPerson.getFirstName())
                            .addLastName((deadPerson.getLastName()))
                            .addGender(deadPerson.getGender())
                            .addBirthDate(deadPerson.getBirthInformation().getBirthDate())
                            .addBirthPlace(deadPerson.getBirthInformation().getBirthPlace())
                            .isDead()
                            .addDeathDate(deathInformation.getDeathDate())
                            .addDeathPlace(deathInformation.getDeathPlace())
                            .build();
        return deadPersonResult;
    }
}
