package com.cmsc495.ticketsystem.service;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void changeTicketAndStatus(Long ticketId, String status, String noteContent) {
        TicketModel ticket = ticketRepository.findById(ticketId).orElseThrow(()
                -> new RuntimeException("Ticket not found"));
        ticket.setTroubleshootingNotes(noteContent);
        ticket.setStatus(status);
        ticketRepository.save(ticket); // This will save ticket, new status, and new note.
    }

    public List<TicketModel> findAllTickets() {
        return ticketRepository.findAll();
    }

    public void saveTicket(TicketModel ticket) {
        ticketRepository.save(ticket);
    }

    public TicketModel findTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}
