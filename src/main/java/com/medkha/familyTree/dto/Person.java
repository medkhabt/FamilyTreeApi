package com.medkha.familyTree.dto;

import java.util.Objects;
import java.util.Optional;


public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;

    private BirthInformation birthInformation;
    private Optional<Person> father;
    private Optional<Person> mother;

    public Optional<Person> getFather() {
        return father;
    }
    public Optional<Person> getMother() {
        return mother;
    }

    protected Person() { }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BirthInformation getBirthInformation() {
        return birthInformation;
    }

    public void setBirthInformation(BirthInformation birthInformation) {
        this.birthInformation = birthInformation;
    }

    public void setFather(Optional<Person> father) {
        this.father = father;
    }

    public void setMother(Optional<Person> mother) {
        this.mother = mother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && gender == person.gender && Objects.equals(birthInformation, person.birthInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender, birthInformation);
    }
}
