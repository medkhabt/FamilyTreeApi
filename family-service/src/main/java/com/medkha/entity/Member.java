package com.medkha.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * familiesTakesPartOf is a set of families that he take part of without having their lastName
 * like being part of your wife's family.
 */
public class Member {
    private final String lastName;
    private final String firstName;
    private final LocalDate birthDate;
    private final String birthPlace;
    private final Optional<LocalDate> deathDate;
    private final Optional<String> deathPlace;

    private final Set<Comment> comments;
    private final Optional<Member> father;
    private final Optional<Member> mother;

    private final Family family;
    private final Set<Family> familiesTakePartOf;

    private final Optional<String> userId;

    public static class Builder {
        private final String lastName;
        private final String firstName;
        private final LocalDate birthDate;
        private final String birthPlace;
        private final Family family;
        private  Optional<LocalDate> deathDate = Optional.empty();
        private  Optional<String> deathPlace = Optional.empty();
        private  final Set<Comment> comments;
        private  Optional<Member> father = Optional.empty();
        private  Optional<Member> mother = Optional.empty();
        private  final Set<Family> familiesTakePartOf;
        private Optional<String> userId = Optional.empty();

        public Builder(String lastName, String firstName, LocalDate birthDate, String birthPlace, Family family) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.birthDate = birthDate;
            this.birthPlace = birthPlace;
            this.family = family;
            this.comments = new HashSet<>();
            this.familiesTakePartOf = new HashSet<>();
        }

        public Builder deathDate(LocalDate deathDate) {
            this.deathDate = Optional.ofNullable(deathDate);
            return this;
        }
        public Builder deathPlace(String deathPlace) {
            this.deathPlace = Optional.ofNullable(deathPlace);
            return this;
        }
        public Builder addComment(Comment comment){
            this.comments.add(comment);
            return this;
        }
        public Builder father(Member father) {
            this.father = Optional.ofNullable(father);
            return this;
        }
        public Builder mother(Member mother) {
            this.mother = Optional.ofNullable(mother);
            return this;
        }
        public Builder addFamilyTakenPartOf(Family family) {
            if(this.family.equals(family)) {
                // log an info or error that this family will not be added
                return this;
            }
            this.familiesTakePartOf.add(family);
            return this;
        }
        public Builder user(String userId) {
            this.userId = Optional.ofNullable(userId);
            return this;
        }
        public Member build() {
            return new Member(this);
        }
    }
    private Member(Builder builder){
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.birthDate = builder.birthDate;
        this.birthPlace = builder.birthPlace;
        this.family = builder.family;
        this.deathDate = builder.deathDate;
        this.deathPlace = builder.deathPlace;
        this.comments = builder.comments;
        this.father = builder.father;
        this.mother = builder.mother;
        this.familiesTakePartOf = builder.familiesTakePartOf;
        this.userId = builder.userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Optional<LocalDate> getDeathDate() {
        return deathDate;
    }

    public Optional<String> getDeathPlace() {
        return deathPlace;
    }

    public Set<Comment> getComments() {
        return new HashSet<>(comments);
    }

    public Optional<Member> getFather() {
        return father;
    }

    public Optional<Member> getMother() {
        return mother;
    }

    public Family getFamily() {
        return family;
    }

    public Set<Family> getFamiliesTakePartOf() {
        return new HashSet<>(familiesTakePartOf);
    }

    public Optional<String> getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return lastName.equals(member.lastName) && firstName.equals(member.firstName) && birthDate.equals(member.birthDate) && birthPlace.equals(member.birthPlace) && deathDate.equals(member.deathDate) && deathPlace.equals(member.deathPlace) && father.equals(member.father) && mother.equals(member.mother) && family.equals(member.family) && familiesTakePartOf.equals(member.familiesTakePartOf) && userId.equals(member.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthDate, birthPlace, deathDate, deathPlace, father, mother, family, familiesTakePartOf, userId);
    }
}
