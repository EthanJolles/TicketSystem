package com.cmsc495.ticketsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TicketModel {

    @Id
    private Long id;
    private String description;
    private boolean isCompleted;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
