package com.example.show_problem.repository;

import com.example.show_problem.entity.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> { }
