package com.example.demo.Controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TierService;
import com.example.demo.Tables.Tier;

@RestController
@RequestMapping("/tier")
public class TierController {

    @Autowired
    private TierService tierService;

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/")
    public String index(){
        return "This is the TIER root route";
    }

    @GetMapping("/all")
    public List<Tier> getAllTiers(){
        return tierService.getAllTiers();
    }

}
