/* ITMS - A CMSC 495 Project
 * Group 2
 * 07 SEP 24
 * This is the TicketService class.
 * This class acts as the service layer between the controller and the repository.
 * It contains methods to interact with the TicketRepository for performing ticket-related operations.
 * These include updating ticket status, adding troubleshooting notes, finding all tickets, saving tickets,
 * and retrieving a specific ticket by ID.
 */

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

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
}
