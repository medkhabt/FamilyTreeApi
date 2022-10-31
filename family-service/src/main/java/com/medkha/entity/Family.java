package com.medkha.entity;

import java.util.Objects;
import java.util.Set;

public class Family {
    private String name;
    private Set<Comment> comments;

    public Family(String name) {
        this.name = name;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: this should change, because two families with the same name may exist.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return name.equals(family.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
