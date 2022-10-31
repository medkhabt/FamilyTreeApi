package com.medkha.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MemberTest {
    @Test
    public void testBuild() {
    // given
    String firstName = "firstName";
    String lastName = "lastName";
    LocalDate birthDate = LocalDate.of(1976, 9, 14);
    String birthPlace = "Somewhere";
    Family family = new Family("family");

    Comment comment = new Comment("random", "randomUser");

    // when
    Member member = new Member.Builder(lastName, firstName, birthDate, birthPlace, family).build();
    Member member1 =
            new Member.Builder(firstName, lastName, birthDate, birthPlace, family)
                    .addComment(comment)
                    .build();
    // then
    assertAll(
            () -> assertEquals(firstName, member.getFirstName()),
            () -> assertEquals(lastName, member.getLastName()),
            () -> assertEquals(birthDate, member.getBirthDate()),
            () -> assertEquals(birthPlace, member.getBirthPlace()),
            () -> assertEquals(family, member.getFamily()),
            () -> assertTrue(member1.getComments().contains(comment))
    );
}
}
