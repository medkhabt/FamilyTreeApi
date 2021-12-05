package com.medkha.familyTree.service_tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.medkha.familyTree.dto.BirthInformation;
import com.medkha.familyTree.dto.Gender;
import com.medkha.familyTree.dto.person.Person;

public class PersonServiceTest {

    @Test
    void createASinglePersonWithoutParents(){
        String firstName = "firstName";
        String lastName = "lastName";
        Gender gender = Gender.MALE;
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.of("place"));

        Person personWithoutParents = new Person.PersonBuilder()
                                            .addFirstName(firstName)
                                            .addLastName(lastName)
                                            .addGender(gender)
                                            .addBirthDate(birthInformation.getBirthDate())
                                            .addBirthPlace(birthInformation.getBirthPlace())
                                            .build();
        assertAll(
                () -> assertEquals(firstName, personWithoutParents.getFirstName()),
                () -> assertEquals(lastName, personWithoutParents.getLastName()),
                () -> assertEquals(birthInformation, personWithoutParents.getBirthInformation()),
                () -> assertTrue(personWithoutParents.getFather().isEmpty()),
                () -> assertTrue(personWithoutParents.getMother().isEmpty()),
                () -> assertEquals(gender, personWithoutParents.getGender())
        );

    }

}
