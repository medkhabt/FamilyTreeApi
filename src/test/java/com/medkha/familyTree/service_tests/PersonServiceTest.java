package com.medkha.familyTree.service_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.medkha.familyTree.dto.BirthInformation;
import com.medkha.familyTree.dto.Person;

public class PersonServiceTest {

    @Test
    void createASinglePersonWithoutParents(){
        String firstName = "firstName";
        String lastName = "lastName";
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.empty());

        Person personWithoutParents = PersonBuilder
                                            .addFirstNameAndLastNameAndBirhtInformation(
                                                    firstName,
                                                    lastName,
                                                    birthInformation)
                                            .build();
        assertAll(
                () -> assertEquals(firstName, personWithoutParents.getFirstName()),
                () -> assertEquals(lastName, personWithoutParents.getLastName()),
                () -> assertEquals(birthInformation, personWithoutParents.getBirthInformation()),
                () -> assertTrue(personWithoutParents.getFather().isEmpty()),
                () -> assertTrue(personWithoutParents.getMother().isEmpty())
        );

    }

}
