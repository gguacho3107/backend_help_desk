package com.example.backendhelpdesk.repository.postgres;

import com.example.backendhelpdesk.model.postgres.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
