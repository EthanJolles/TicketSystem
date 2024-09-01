package com.cmsc495.ticketsystem.model;

import jakarta.persistence.*;

@Entity
public class NotesModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TicketModel ticket;

    private String note;

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

    public String getNote() {
        return note;
    }
}
