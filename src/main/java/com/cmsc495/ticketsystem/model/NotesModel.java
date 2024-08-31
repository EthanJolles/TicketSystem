package com.cmsc495.ticketsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NotesModel {


    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String note;
}
