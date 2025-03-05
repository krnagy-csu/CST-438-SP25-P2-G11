package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.TierRepo;
import com.example.demo.Tables.Tier;

@Service
public class TierService {
    @Autowired
    private TierRepo tierRepo;

    public List<Tier> getAllTiers(){
        return tierRepo.findAll();
    }

    public Tier addTier(Tier tier){
        return tierRepo.save(tier);
    }

}
