package com.example.demo.Controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TierService;
import com.example.demo.Tables.Tier;
import com.example.demo.Tables.User;

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

    @GetMapping("/{id}")
    public Tier getTier(@PathVariable Integer id){
        return tierService.getTier(id);
    }

    //THIS RETURNS A LIST OF TIERS WITH THE SAME SUBJECT
    //could help when working with machine learning stuff to match the tiers with same subject
    @GetMapping("/{subject}")
    public List<Tier> getTiersBySubject(@PathVariable String subject){
        return tierService.getTiersBySubject(subject);
    }

    @PostMapping("/add")
    public void addTier(@RequestBody Tier tier){
        tierService.addTier(tier);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTier(@PathVariable Integer id){
        tierService.deleteTier(id);
    }

    @PatchMapping("/edit/{id}")
    public void editTier(@PathVariable Integer id, @RequestBody Tier tierChanges){
        tierService.editTier(id, tierChanges);
    }

    @PutMapping("/change/{id}")
    public void putTier(@PathVariable Integer id, @RequestBody Tier tierChanges){
        tierService.putTier(id, tierChanges);
    }

}
