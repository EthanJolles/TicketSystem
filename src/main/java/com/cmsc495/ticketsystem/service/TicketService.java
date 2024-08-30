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

    public List<TicketModel> findAllTickets() {
        return ticketRepository.findAll();
    }

    public TicketModel saveTicket(TicketModel ticket) {
        return ticketRepository.save(ticket);
    }
}
