package com.medkha.familyTree.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.medkha.familyTree.dto.BirthInformation;
import com.medkha.familyTree.dto.DeathInformation;
import com.medkha.familyTree.dto.Gender;
import com.medkha.familyTree.dto.Marriage;
import com.medkha.familyTree.dto.Person;
import com.medkha.familyTree.service.impl.FamilyActionsServiceImpl;

@ExtendWith(SpringExtension.class)
public class FamilyActionsServiceTest {

    private FamilyActionsService familyActionsService;

    public FamilyActionsServiceTest() {
        familyActionsService = new FamilyActionsServiceImpl();
    }
    @Test
    void marryASinglePerson(){
        String firstName = "firstName";
        String lastName = "lastName";
        Gender gender = Gender.MALE;
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.of("place"));

        Person partnerOfTheMarriedPerson = new Person.PersonBuilder()
                .addLastName("partnerLastName")
                .addFirstName("partnerFirstName")
                .addGender(Gender.FEMALE)
                .build();
        Person marriedPersonWithOnePartner = new Person.PersonBuilder()
                .addLastName(lastName)
                .addFirstName(firstName)
                .addGender(gender)
                .build();

        Marriage marriage =
                this.familyActionsService.marriage(
                        marriedPersonWithOnePartner,
                        partnerOfTheMarriedPerson,
                        Optional.empty(),
                        Optional.empty());

        assertAll(
                () -> assertTrue(marriage.getCouple().getKey().equals(marriedPersonWithOnePartner) ||
                        marriage.getCouple().getValue().equals(marriedPersonWithOnePartner)
                ),
                () -> assertTrue(marriage.getCouple().getKey().equals(partnerOfTheMarriedPerson) ||
                        marriage.getCouple().getValue().equals(partnerOfTheMarriedPerson))
        );
    }

    @Test
    void deathOfSinglePerson() {

        String firstName = "firstName";
        String lastName = "lastName";
        Gender gender = Gender.MALE;
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.of("place"));

        Person person = new Person.PersonBuilder()
                .addFirstName(firstName)
                .addLastName(lastName)
                .addGender(gender)
                .addBirthDate(birthInformation.getBirthDate())
                .addBirthPlace(birthInformation.getBirthPlace())
                .build();

        Person personDied = this.familyActionsService.death(person, new DeathInformation(Optional.of(LocalDate.now()), Optional.of("somewhere")));

        assertFalse(personDied.getDeathInformation().isEmpty());
    }

    @Test
    void deathOfSinglePersonWithNoDeathInformation() {
        String firstName = "firstName";
        String lastName = "lastName";
        Gender gender = Gender.MALE;
        BirthInformation birthInformation = new BirthInformation(Optional.empty(), Optional.of("place"));

        Person person = new Person.PersonBuilder()
                .addFirstName(firstName)
                .addLastName(lastName)
                .addGender(gender)
                .addBirthDate(birthInformation.getBirthDate())
                .addBirthPlace(birthInformation.getBirthPlace())
                .build();

        Person personDied = this.familyActionsService.death(person, new DeathInformation(Optional.empty(), Optional.empty()));
        assertFalse(personDied.getDeathInformation().isEmpty());
    }
}
