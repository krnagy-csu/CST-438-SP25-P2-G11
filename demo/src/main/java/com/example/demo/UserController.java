package com.example.demo;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import com.example.demo.User;

@RestController
public class UserController {

    private final AtomicInteger counter = new AtomicInteger();
    
    @GetMapping("/")
    public String index() {
        return "THIS IS THE ROOT ROUTE";
    }

    //On the website URL you can set username?=(anything) and it will display a user object with that name
    @GetMapping("/user")
    public User showUser(@RequestParam(value = "username", defaultValue = "Sebastian") String username, @RequestParam(value = "password", defaultValue = "123456") String password){
        User user = new User(counter.incrementAndGet(), username, password); 
        return user;
    }

    //this will be worked on the future once we set up the tables for our database
    @PostMapping("/addUser")
    public User addUser(User user){
        return user;
    }

    
}
