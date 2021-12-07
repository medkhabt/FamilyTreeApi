package com.medkha.familyTree.dto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PersonBuilderTest {

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

    @Test
    void createASinglePersonWithParents(){

        Person father = new Person.PersonBuilder()
                                .addFirstName("father")
                                .addLastName("lastNameFather")
                                .addGender(Gender.MALE)
                                .addBirthPlace(Optional.of("somewhere in the jgl"))
                                .build();

        Person mother = new Person.PersonBuilder()
                .addFirstName("mother")
                .addLastName("lastNameMother")
                .addGender(Gender.FEMALE)
                .addBirthPlace(Optional.of("somewhere on the mountain"))
                .build();

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
                .addFather(Optional.of(father))
                .addMother(Optional.of(mother))
                .build();
        assertAll(
                () -> assertEquals(firstName, personWithoutParents.getFirstName()),
                () -> assertEquals(lastName, personWithoutParents.getLastName()),
                () -> assertEquals(birthInformation, personWithoutParents.getBirthInformation()),
                () -> assertEquals(Optional.of(father), personWithoutParents.getFather()),
                () -> assertEquals(Optional.of(mother), personWithoutParents.getMother()),
                () -> assertEquals(gender, personWithoutParents.getGender())
        );
    }

    @Test
    void shouldThrowException_when_notValidPersonObjectIsTryingToGetBuilt(){
        String firstName = "firstName";
        String lastName = "lastName";
        Gender gender = Gender.MALE;
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.of("place"));
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, ()-> {
                    Person personWithNoGender = new Person.PersonBuilder()
                            .addFirstName(firstName)
                            .addLastName(lastName)
                            .build();
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    Person personWithNoFirstName = new Person.PersonBuilder()
                            .addLastName("lastName")
                            .addGender(gender)
                            .build();
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    Person personWithNoLastName = new Person.PersonBuilder()
                            .addFirstName("firstName")
                            .addGender(gender)
                            .build();
                })
        );


    }



    @Test
    void createADivorcedPersonWhoOnceWasMarried(){

    }

    @Test
    void createAMarriedPersonWithMultiplePartners(){

    }
    @Test
    void createAMarriedPersonWhoOnceWasDivorced(){

    }

}
