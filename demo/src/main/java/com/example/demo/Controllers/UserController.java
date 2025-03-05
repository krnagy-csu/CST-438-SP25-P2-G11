// https://medium.com/@chandantechie/spring-boot-application-with-crud-operations-using-spring-data-jpa-and-mysql-23c8019660b1 about CRUD operation using JPA
// https://spring.io/guides/gs/accessing-data-mysql accessing data in Springboot **IMPORTANT** shows steps on how to work with database

package com.example.demo.Controllers;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Service.UserService;
import com.example.demo.Tables.User;
import com.example.demo.Tables.UserRecord;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final AtomicInteger counter = new AtomicInteger();
    
    @GetMapping("/")
    public String index() {
        return "THIS IS THE USER ROOT ROUTE";
    }

    //this one works and gets all the users from the database
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //On the website URL you can set username?=(anything) and it will display a user object with that name
    // @GetMapping("/info")
    // public User showUser(@RequestParam(value = "username", defaultValue = "Sebastian") String username, @RequestParam(value = "password", defaultValue = "123456") String password){
    //     User user = new User(username, password); 
    //     return user;
    // }

    //this will be worked on the future once we set up the tables for our database
    // @PostMapping("/add")
    // public User addUser(User user){
    //     return user;
    // }

    @GetMapping("/deleteUser")
    public boolean deleteUser(User user){return userService.deleteUser();}

    
}
