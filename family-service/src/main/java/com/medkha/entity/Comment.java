package com.medkha.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private String content;
    // TODO: comment with tags ?
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private String lastUpdatedByUserId;

    public Comment(String content, LocalDateTime createdOn, LocalDateTime lastUpdatedOn, String lastUpdatedByUserId) {
        this.content = content;
        this.createdOn = createdOn;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }

    /* TODO: Not sure if this is a good idea, the service should take in consideration this.
        i'll keep it for now but maybe i should remove it.
    */
    /**
     *
     * @param content: the content of the message
     * @param createdByUserId : user id who created the comment.
     */
    public Comment(String content, String createdByUserId) {
        this.content = content;
        this.createdOn = LocalDateTime.now();
        this.lastUpdatedOn = LocalDateTime.now();
        this.lastUpdatedByUserId = createdByUserId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public void setLastUpdatedByUserId(String lastUpdatedByUserId) {
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public String getLastUpdatedByUserId() {
        return lastUpdatedByUserId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return content.equals(comment.content) && createdOn.equals(comment.createdOn) && lastUpdatedOn.equals(comment.lastUpdatedOn) && lastUpdatedByUserId.equals(comment.lastUpdatedByUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, createdOn, lastUpdatedOn, lastUpdatedByUserId);
    }
}
