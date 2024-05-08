package com.poc.makemytrip.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.makemytrip.config.AuthenticationRequest;
import com.poc.makemytrip.config.AuthenticationResponse;
import com.poc.makemytrip.config.RegisterRequest;
import com.poc.makemytrip.entity.User;
import com.poc.makemytrip.service.HomeService;
import com.poc.makemytrip.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    HomeService hm;
    @Autowired
    LoginService ls;

    // Home Page of MMT
    @RequestMapping("/")
    public String home(){
        return "index";
    }

    // Signup page
    @GetMapping("/signup")
    public String signup(User user){
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request){
        ResponseEntity<AuthenticationResponse> response = ResponseEntity.ok(ls.register(request));
        return "redirect:/";
    }

    // Login Page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/authentication")
    public String authenticate(@ModelAttribute AuthenticationRequest request, HttpServletResponse httpResponse){
        try {
            AuthenticationResponse res = ls.authenticate(request);
            System.out.println(res);
        }catch (Exception ex){
            System.out.println("Invalid Credentails");
            return "redirect:/login";
        }
        return "redirect:/flights";
    }


    @RequestMapping("/getall")
    @ResponseBody
    public List<User> getAll() throws JsonProcessingException {
        List<User> list = hm.getAll();
        return list;
    }


}
