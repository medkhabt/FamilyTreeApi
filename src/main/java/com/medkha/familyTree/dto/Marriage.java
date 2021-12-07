package com.medkha.familyTree.dto;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Objects;
import java.util.Optional;

public class Marriage {
    private final AbstractMap.SimpleImmutableEntry<Person, Person> couple;
    private final Optional<LocalDate> dateOfMarriage;
    private final Optional<LocalDate> dateEndOfMarriage;
    public Marriage(MarriageBuilder marriageBuilder) {
        this.couple = new AbstractMap.SimpleImmutableEntry(marriageBuilder.couple);
        this.dateOfMarriage = marriageBuilder.dateOfMarriage;
        this.dateEndOfMarriage = marriageBuilder.dateEndOfMarriage;
    }

    public AbstractMap.SimpleImmutableEntry<Person, Person> getCouple() {
        return new AbstractMap.SimpleImmutableEntry(couple);
    }

    public Optional<LocalDate> getDateOfMarriage() {
        return dateOfMarriage;
    }

    public Optional<LocalDate> getDateEndOfMarriage() {
        return dateEndOfMarriage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marriage marriage = (Marriage) o;
        return couple.equals(marriage.couple) && dateOfMarriage.equals(marriage.dateOfMarriage) && dateEndOfMarriage.equals(marriage.dateEndOfMarriage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couple, dateOfMarriage, dateEndOfMarriage);
    }

    public static class MarriageBuilder {
        private AbstractMap.SimpleEntry<Person, Person> couple;
        private Optional<LocalDate> dateOfMarriage;
        private Optional<LocalDate> dateEndOfMarriage;

        public MarriageBuilder(){
            dateOfMarriage = Optional.empty();
            dateEndOfMarriage = Optional.empty();
        }

        public MarriageBuilder addCouple(Person person1, Person person2) {
            this.couple = new SimpleEntry<>(person1, person2);
            return this;
        }
        public MarriageBuilder addDateOfMarriage(LocalDate dateOfMarriage) {
            this.dateOfMarriage = Optional.ofNullable(dateOfMarriage);
            return this;
        }
        public MarriageBuilder addDateEndOfMarriage(LocalDate endOfMarriageDate) {
            this.dateEndOfMarriage = Optional.ofNullable(endOfMarriageDate);
            return this;
        }
        public Marriage build() {
            return new Marriage(this);
        }
    }

}
