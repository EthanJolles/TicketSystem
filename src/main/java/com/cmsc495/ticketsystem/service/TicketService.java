package com.cmsc495.ticketsystem.service;

import com.cmsc495.ticketsystem.model.ticket.NotesModel;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void addNoteToTicket(Long ticketId, String noteContent) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.addNoteToTicket(noteContent);
        ticketRepository.save(ticket); // This will save both the ticket and the new note
    }

    public void removeNoteFromTicket(Long ticketId, Long noteId) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        NotesModel note = ticket.getNotes().stream()
                .filter(n -> n.getId().equals(noteId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Note not found"));

        ticket.removeNoteFromTicket(note);
        ticketRepository.save(ticket); // This will remove the note from the ticket and delete it from the database
    }

    public String getFormattedCreationDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }

    public List<TicketModel> findAllTickets() {
        return ticketRepository.findAll();
    }

    public TicketModel saveTicket(TicketModel ticket) {
        return ticketRepository.save(ticket);
    }
}
