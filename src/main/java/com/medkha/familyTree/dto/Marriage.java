package com.medkha.familyTree.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.medkha.familyTree.dto.person.Person;

public class Marriage {
    private final Set<Person> partners;
    private final Optional<LocalDate> dateOfMarriage;
    private final Optional<LocalDate> dateEndOfMarriage;
    public Marriage(MarriageBuilder marriageBuilder) {
        this.partners = Set.copyOf(marriageBuilder.partners);
        this.dateOfMarriage = marriageBuilder.dateOfMarriage;
        this.dateEndOfMarriage = marriageBuilder.dateEndOfMarriage;
    }

    public Set<Person> getPartners() {
        return new HashSet<>(partners);
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
        return partners.equals(marriage.partners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partners);
    }
    public static class MarriageBuilder {
        private Set<Person> partners;
        private Optional<LocalDate> dateOfMarriage;
        private Optional<LocalDate> dateEndOfMarriage;

        public MarriageBuilder(){
            partners = new HashSet<>();
            dateOfMarriage = Optional.empty();
            dateEndOfMarriage = Optional.empty();
        }

        public MarriageBuilder addPartner(Person person) {
            return this;
        }
        public MarriageBuilder addDateOfMarriage(LocalDate dateOfMarriage) {
            return this;
        }
        public MarriageBuilder addDateEndOfMarriage(LocalDate endOfMarriageDate) {
            return this;
        }
        public Marriage build() {
            return new Marriage(this);
        }
    }

}
