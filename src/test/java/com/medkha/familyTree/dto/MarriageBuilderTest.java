package com.medkha.familyTree.dto;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class MarriageBuilderTest {

    public List<Person> listOfPeople(){
        return Stream.of(
                new Person.PersonBuilder()
                        .addFirstName("p1FirstName")
                        .addLastName("p1LastName")
                        .addGender(Gender.FEMALE)
                        .build(),
                new Person.PersonBuilder()
                        .addFirstName("p2FirstName")
                        .addLastName("p2LastName")
                        .addGender(Gender.MALE)
                        .build(),
                new Person.PersonBuilder()
                        .addFirstName("p3FirstName")
                        .addLastName("p3LastName")
                        .addGender(Gender.FEMALE)
                        .build()
        ).collect(Collectors.toList());
    }
    @Test
    public void marryTwoPeopleWithoutDateOfMarriageAndStillMarried() {

        Marriage marriage = new Marriage.MarriageBuilder()
                                    .addCouple(listOfPeople().get(0), listOfPeople().get(1))
                                    .build();
        assertTrue(
                marriage.getCouple().getKey().equals(listOfPeople().get(0)) ||
                marriage.getCouple().getValue().equals(listOfPeople().get(0)));
        assertTrue(
                marriage.getCouple().getKey().equals(listOfPeople().get(1)) ||
                        marriage.getCouple().getValue().equals(listOfPeople().get(1)));

    }

    @Test
    public void marryMoreThanTwoPeople() {
        Marriage marriage1 = new Marriage.MarriageBuilder()
                                .addCouple(listOfPeople().get(1), listOfPeople().get(0))
                                .build();
        Marriage marriage2 = new Marriage.MarriageBuilder()
                                .addCouple(listOfPeople().get(1), listOfPeople().get(2))
                                .build();
        assertTrue(
                marriage1.getCouple().getKey().equals(listOfPeople().get(1)) ||
                        marriage1.getCouple().getValue().equals(listOfPeople().get(1))
        );
        assertTrue(
                marriage2.getCouple().getKey().equals(listOfPeople().get(1)) ||
                        marriage2.getCouple().getValue().equals(listOfPeople().get(1))
        );
    }

    @Test
    public void marryADivorsedPerson() {
        Marriage firstMarriage = new Marriage.MarriageBuilder()
                .addCouple(listOfPeople().get(1), listOfPeople().get(0))
                .addDateEndOfMarriage(Optional.of(LocalDate.of(2010, 03, 12)))
                .build();
        Marriage marriageAfterDivorce = new Marriage.MarriageBuilder()
                .addCouple(listOfPeople().get(0), listOfPeople().get(2))
                .addDateOfMarriage(Optional.of(LocalDate.of(2014, 10, 05)))
                .build();
        assertTrue(marriageAfterDivorce.getCouple().getKey().equals(listOfPeople().get(0)) ||
                marriageAfterDivorce.getCouple().getValue().equals(listOfPeople().get(0))
        );
    }

    @Test
    public void validateMarriage() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Marriage.MarriageBuilder()
                            .addCouple(listOfPeople().get(0), listOfPeople().get(0))
                            .build();
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Marriage.MarriageBuilder()
                            .addCouple(listOfPeople().get(0), listOfPeople().get(1))
                            .addDateOfMarriage(Optional.of(LocalDate.of(2012,12,04)))
                            .addDateEndOfMarriage(Optional.of(LocalDate.of(2012,10,14)))
                            .build();
                })
        );
    }
}
