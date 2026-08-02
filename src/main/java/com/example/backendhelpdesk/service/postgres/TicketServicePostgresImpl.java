package com.example.backendhelpdesk.service.postgres;

import com.example.backendhelpdesk.exception.TicketNotFoundException;
import com.example.backendhelpdesk.model.enums.State;
import com.example.backendhelpdesk.model.postgres.Ticket;
import com.example.backendhelpdesk.repository.postgres.TicketRepository;
import com.example.backendhelpdesk.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServicePostgresImpl implements TicketService {
    private final TicketRepository repository;

    public TicketServicePostgresImpl(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        if (ticket.getStatus() == null){
            ticket.setStatus(State.ABIERTO);
        }
        return repository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        return repository.findById(id)
                .map(ticketFound -> {
                    ticketFound.setDescription(ticket.getDescription());
                    ticketFound.setCategory(ticket.getCategory());
                    ticketFound.setPriority(ticket.getPriority());
                    ticketFound.setStatus(ticket.getStatus());
                    return repository.save(ticketFound);
                }).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Override
    public void deleteTicket(Long id) {
        if (!repository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
