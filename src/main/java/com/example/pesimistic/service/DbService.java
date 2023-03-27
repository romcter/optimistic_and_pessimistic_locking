package com.example.pesimistic.service;

import com.example.pesimistic.entity.Flight;
import com.example.pesimistic.entity.Ticket;
import com.example.pesimistic.repository.FlightRepository;
import com.example.pesimistic.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class DbService {

    private final FlightRepository flightRepository;

    private final TicketRepository ticketRepository;

    public DbService(FlightRepository flightRepository, TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    private void fetchAndChangeFlight(long flightId) throws Exception {
        var flight = flightRepository.findWithLockingById(flightId).get();
        flight.setCapacity(flight.getCapacity() + 1);
        Thread.sleep(1_000);
    }

    @Transactional
    public void changeFlight1() throws Exception {
        fetchAndChangeFlight(1L);
        fetchAndChangeFlight(2L);
        Thread.sleep(1_000);
    }

    @Transactional
    public void changeFlight2() throws Exception {
        fetchAndChangeFlight(2L);
        fetchAndChangeFlight(1L);
        Thread.sleep(1_000);
    }

    @Transactional
    public void loadData(){
        Flight flight = new Flight("12345", LocalDateTime.now(), 1, null);
        var ticket = Set.of(
                new Ticket(flight,"Name", "LastName")
        );
        flight.setTickets(ticket);
        flightRepository.save(flight);
    }

}
