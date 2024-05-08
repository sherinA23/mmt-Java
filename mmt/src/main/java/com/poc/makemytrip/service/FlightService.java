package com.poc.makemytrip.service;

import com.poc.makemytrip.dao.AircraftDao;
import com.poc.makemytrip.dao.FlightDao;
import com.poc.makemytrip.entity.Aircraft;
import com.poc.makemytrip.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightDao flight_dao;
    @Autowired
    AircraftDao aircraft_dao;


    public List<Flight> getAllFlights() {
        return flight_dao.findAll();
    }

    public Boolean create(Aircraft aircraft, Flight flight) {
        aircraft_dao.save(aircraft);
        flight.setAircraft(aircraft);
        flight_dao.save(flight);
        return true;
    }

    public List<Flight> search(String source, String destination, String date, Integer nop) {
        return flight_dao.findBySourceDestDateNop(source, destination,date,nop);
    }
}
