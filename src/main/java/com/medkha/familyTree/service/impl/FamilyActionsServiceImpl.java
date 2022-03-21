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
        return null;
    }

    @Override
    public void death(Person deadPerson, DeathInformation deathInformation) {

    }
}
