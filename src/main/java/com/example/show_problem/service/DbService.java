package com.example.show_problem.service;

import com.example.show_problem.entity.Flight;
import com.example.show_problem.entity.Ticket;
import com.example.show_problem.repository.FlightRepository;
import com.example.show_problem.repository.TicketRepository;
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

    private void saveNewTicket(String firstName, String lastName, Flight flight) throws Exception {
        if (flight.getCapacity() <= flight.getTickets().size()) {
            throw new Exception();
        }
        var ticket = new Ticket();
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        flight.addTicket(ticket);
        ticketRepository.save(ticket);
    }

    @Transactional
    public void changeFlight1() throws Exception {
        var flight = flightRepository.findById(1L).get();
        saveNewTicket("Robert", "Smith", flight);
        Thread.sleep(1_000);
    }

    @Transactional
    public void changeFlight2() throws Exception {
        var flight = flightRepository.findById(1L).get();
        saveNewTicket("Kate", "Brown", flight);
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

    @Transactional
    public void findAllFlight(){
        System.out.println("==================================================================================");
        System.out.println(flightRepository.findAll());
    }

}
