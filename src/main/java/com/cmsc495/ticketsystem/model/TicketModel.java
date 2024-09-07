package com.cmsc495.ticketsystem.model;

import jakarta.persistence.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

@Entity
public class TicketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDate creationDate;

    private String name;

    private String email;

    private String department;

    private String issueType;

    private String description;

    private String troubleshootingNotes;

    public TicketModel() {};

    public TicketModel(String name, String email, String department, String issueType, String description) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.issueType = issueType;
        this.description = description;
        this.creationDate = LocalDate.now();
    }

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = "New"; // Set default value if not already set
        }
        if (creationDate == null) {
            creationDate = LocalDate.now(); // Set default creation date
        }
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

    public String getIssueType() {
        return issueType;
    }
    
    public String getDescription() {
        return description;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getFormattedDate() {
        return this.creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    public String getTroubleshootingNotes() {
        return troubleshootingNotes;
    }
    
    public void setTroubleshootingNotes(String troubleshootingNotes) {
        this.troubleshootingNotes = troubleshootingNotes;
    }
}
