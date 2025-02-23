package com.example.demo;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.example.demo.User;

@RestController
public class UserController {
    
    @GetMapping("/")
    public String index() {
        return "THIS IS THE ROOT ROUTE";
    } 

    @GetMapping("/user")
    public User showUser(){
        User user = new User(1, "Sebastian", "123456");
        return user;
    }
}
