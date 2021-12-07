package com.medkha.familyTree.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.medkha.familyTree.dto.BirthInformation;
import com.medkha.familyTree.dto.Gender;
import com.medkha.familyTree.dto.Marriage;
import com.medkha.familyTree.dto.Person;
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FamilyActionsServiceTest {
    private FamilyActionsService familyActionsServiceMock;

    @BeforeEach
    public void setupMock() {
        familyActionsServiceMock = mock(FamilyActionsService.class);
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
                this.familyActionsServiceMock.marriage(
                        marriedPersonWithOnePartner,
                        partnerOfTheMarriedPerson,
                        Optional.empty(),
                        Optional.empty());

        assertAll(
                () -> assertTrue(marriage.getPartners().contains(marriedPersonWithOnePartner)),
                () -> assertTrue(marriage.getPartners().contains(partnerOfTheMarriedPerson))
        );
    }
}
