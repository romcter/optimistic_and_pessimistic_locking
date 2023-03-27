package com.example.show_problem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDateTime departureTime;

    private Integer capacity;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        ticket.setFlight(this);
        getTickets().add(ticket);
    }

    public Flight(String number, LocalDateTime departureTime, Integer capacity, Set<Ticket> tickets) {
        this.number = number;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", departureTime=" + departureTime +
                ", capacity=" + capacity +
                ", tickets=" + tickets +
                '}';
    }
}