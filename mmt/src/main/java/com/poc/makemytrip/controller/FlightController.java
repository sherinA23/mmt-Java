package com.poc.makemytrip.controller;

import com.poc.makemytrip.entity.Aircraft;
import com.poc.makemytrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poc.makemytrip.entity.Flight;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("")
    public String home() {
        return "flights";
    }

    @GetMapping("add")
    public String add(Aircraft aircraft, Flight flight) {
        return "addFlight";
    }

    @PostMapping("/create")
    public String create(Aircraft aircraft, Flight flight) {
        System.out.println(aircraft);
        System.out.println(flight);
        if (!flightService.create(aircraft, flight))
            System.out.println("Flight not created");

        return "redirect:/flights";
    }

//    public List<Flight> getAllFlights(){
//        return fs.getAllFlights();
//    }

    @GetMapping("/search")
    public String search(Model model, Flight flight) {
        model.addAttribute("iframeSrc", "");
        return "searchFlight";
    }

    @PostMapping("/search")
    public String search(@RequestParam("source") String source,
                               @RequestParam("destination") String destination,
                               @RequestParam("date") String date,
                               @RequestParam("no_of_passengers") Integer nop, Model model)
    {
        List<Flight> flights = flightService.search(source, destination, date, nop);
        model.addAttribute("flights", flights);
//        model.addAttribute("iframeSrc", "/searchResult.html");
        System.out.println(flights);
        return "searchResult";
    }
}
