package com.cmsc495.ticketsystem.model;

import com.cmsc495.ticketsystem.model.ticket.NotesModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TicketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate creationDate;

    private String department;

    private String issueType;

    private String description;
    private boolean isCompleted = false;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotesModel> notes = new ArrayList<>();

    public TicketModel() {};

    public TicketModel(String name, String email, String department, String issueType, String description) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.issueType = issueType;
        this.description = description;
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIssueType() {
        return issueType;
    }

    public List<NotesModel> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NotesModel> notes) {
        this.notes = notes;
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

    public void addNoteToTicket(String noteContent) {
        NotesModel note = new NotesModel();
        note.setNote(noteContent);
        note.setTicket(this); // Set the current ticket as the owner of the note
        this.notes.add(note); // Add the note to the list of notes
    }

    public void removeNoteFromTicket(NotesModel note) {
        this.notes.remove(note);
        note.setTicket(null); // Break the association with this ticket
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
