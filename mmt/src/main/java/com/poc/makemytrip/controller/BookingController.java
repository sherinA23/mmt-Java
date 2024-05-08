package com.poc.makemytrip.controller;

import com.poc.makemytrip.entity.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    // List all the booking of the current user ID
    @GetMapping("/index")
    public String index() {return "index";}

    // Create new booking for the current user ID
    @PostMapping("/new")
    public String create(@RequestParam String id){
        System.out.println(id);
        return "booking";
    }

    // Cancel booking feature
    @RequestMapping("delete")
    @ResponseBody
    public String delete() {return "delete";}
}
