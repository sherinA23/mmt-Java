package com.poc.makemytrip.controller;


import com.poc.makemytrip.service.HomeService;
import com.poc.makemytrip.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("passenger")
public class PassengerController {

    //List all the passengers of the given user ID
    @GetMapping("/index")
    @ResponseBody
    public String index(){
        return "index";
    }

    //Add new passenger
    @PostMapping("/new")
    @ResponseBody
    public String create() {return "create";}
}
