package com.example.demo.Controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier")
public class TierController {
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/")
    public String index(){
        return "This is the tier root route";
    }

}
