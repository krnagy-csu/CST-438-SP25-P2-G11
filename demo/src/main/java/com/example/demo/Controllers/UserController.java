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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.demo.Service.UserService;
import com.example.demo.Tables.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "THIS IS THE USER ROOT ROUTE";
    }

    //this one works and gets all the users from the database
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PatchMapping("/editUser/{id}")
    public boolean editUser(@PathVariable Integer id, @RequestBody User userUpdates) {
        return userService.editUser(id,userUpdates);
    }

    @PutMapping("/change/{id}")
    public void putUser(@PathVariable Integer id, @RequestBody User userUpdates){
        userService.putUser(id, userUpdates);
    }
}
