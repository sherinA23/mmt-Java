package com.poc.makemytrip.service;

import com.poc.makemytrip.dao.UsersDao;
import com.poc.makemytrip.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    public static String get(){
        return "In home Page";
    }

    @Autowired
    UsersDao usersdao;
    public List<User> getAll(){
        return usersdao.findAll();
    }
}
