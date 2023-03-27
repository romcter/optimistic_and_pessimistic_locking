package com.example.pesimistic.repository;


import com.example.pesimistic.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> { }
