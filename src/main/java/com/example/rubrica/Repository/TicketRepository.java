package com.example.rubrica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rubrica.model.Ticket;

@Repository
public interface TicketRepository<T> extends JpaRepository<Ticket, Long> {

}
