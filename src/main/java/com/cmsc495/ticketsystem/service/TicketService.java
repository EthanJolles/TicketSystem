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

    /**
     * Changes the status of a ticket and adds troubleshooting notes.
     * @param ticketId ID of the ticket to be updated.
     * @param status New status for the ticket.
     * @param noteContent Troubleshooting notes to be added.
     * @return The updated TicketModel object.
     */
    public TicketModel changeTicketAndStatus(Long ticketId, String status, String noteContent) {
        TicketModel ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new RuntimeException("Ticket not found"));
        ticket.setTroubleshootingNotes(noteContent);
        ticket.setStatus(status);
        ticketRepository.save(ticket); // Save the ticket with the new status and notes
        return ticket;
    }

    /**
     * Retrieves all tickets from the repository.
     * @return List of all TicketModel objects.
     */
    public List<TicketModel> findAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Saves a new or existing ticket to the repository.
     * @param ticket The TicketModel object to be saved.
     */
    public void saveTicket(TicketModel ticket) {
        ticketRepository.save(ticket);
    }

    /**
     * Retrieves a specific ticket by its ID.
     * @param id The ID of the ticket to be retrieved.
     * @return The TicketModel object with the specified ID.
     */
    public TicketModel findTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ticket not found"));
    }

    /**
     * Deletes a ticket by its ID.
     * @param id The ID of the ticket to be deleted.
     */
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
}
