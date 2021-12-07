package com.medkha.familyTree.dto;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MarriageBuilderTest {
    @Test
    public void marryTwoPeopleWithoutDateOfMarriageAndStillMarried() {
        Person person1 = new Person.PersonBuilder()
                                .addFirstName("p1FirstName")
                                .addLastName("p1LastName")
                                .addGender(Gender.FEMALE)
                                .build();
        Person person2 = new Person.PersonBuilder()
                                .addFirstName("p2FirstName")
                                .addLastName("p2LastName")
                                .addGender(Gender.MALE)
                                .build();
        Marriage marriage = new Marriage.MarriageBuilder()
                                    .addPartner(person1)
                                    .addPartner(person2)
                                    .build();
        assertTrue(marriage.getPartners().size() == 2);
        assertTrue(marriage.getPartners().contains(person1));
        assertTrue(marriage.getPartners().contains(person2));

    }
}
