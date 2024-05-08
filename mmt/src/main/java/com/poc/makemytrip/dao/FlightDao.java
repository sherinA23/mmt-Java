package com.poc.makemytrip.dao;

import com.poc.makemytrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.poc.makemytrip.entity.Flight;

import java.util.List;

@Repository
public interface FlightDao extends JpaRepository<Flight, Integer>  {

    @Query(value = "select f from Flight f where f.source = ?1 and f.destination = ?2 and f.date = ?3 and f.availability >= ?4")
    List<Flight> findBySourceDestDateNop(String source, String  destination, String date, Integer nop);
}
