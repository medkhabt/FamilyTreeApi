package com.medkha.familyTree.dto.person;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import com.medkha.familyTree.dto.BirthInformation;
import com.medkha.familyTree.dto.Gender;


public class Person {
    private final String firstName;
    private final String lastName;
    private final Gender gender;

    private final BirthInformation birthInformation;
    private final Optional<Person> father;
    private final Optional<Person> mother;

    public Optional<Person> getFather() {
        return father;
    }
    public Optional<Person> getMother() {
        return mother;
    }

    public Person(PersonBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthInformation = builder.birthInformation;
        this.gender = builder.gender;
        this.father = builder.father;
        this.mother = builder.mother;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Gender getGender() {
        return gender;
    }
    public BirthInformation getBirthInformation() {
        return birthInformation;
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

    public static class PersonBuilder{
        private String firstName;
        private String lastName;
        private Gender gender;

        private BirthInformation birthInformation;
        private Optional<Person> father;
        private Optional<Person> mother;

        public PersonBuilder(){
            this.birthInformation = new BirthInformation(Optional.empty(), Optional.empty());
            this.father = Optional.empty();
            this.mother = Optional.empty();
        }
        public PersonBuilder addFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder addLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder addBirthDate(Optional<LocalDate> birthDate) {
            this.birthInformation.setBirthDate(birthDate);
            return this;
        }

        public PersonBuilder addBirthPlace(Optional<String> birthPlace) {
            this.birthInformation.setBirthPlace(birthPlace);
            return this;
        }

        public PersonBuilder addGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder addFather(Optional<Person> father) {
            this.father = father;
            return this;
        }

        public PersonBuilder addMother(Optional<Person> mother) {
            this.mother = mother;
            return this;
        }

        public Person build() {
            Person person = new Person(this);
            validatePersonObject(person);
            return person; 
        }

        private void validatePersonObject(Person person) {
            if(person.gender == null
                    || person.firstName == null || person.firstName.isEmpty()
                    || person.lastName == null || person.lastName.isEmpty()) {
                throw new IllegalArgumentException("Can't build a person instance. The person instance needs at least a"
                        + "FistName, LastName and a Gender to be built.");
            }
        }


    }
}
