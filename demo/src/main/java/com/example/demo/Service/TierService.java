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

    public Tier getTier(Integer id){
        return tierRepo.findById(id).orElse(null);
    }

    public List<Tier> getTiersBySubject(String subject){
        return tierRepo.findBySubject(subject);
    }

    public void addTier(Tier tier){
        tierRepo.save(tier);
    }

    public void deleteTier(Integer id){
        if(tierRepo.existsById(id)){
            tierRepo.deleteById(id);
        }
    }

    public void editTier(Integer id, Tier changesToTier){
        // TODO
        Tier tierByID = tierRepo.findById(id).orElse(null);

        if(tierByID == null){
            System.err.println("tierByID: " + tierByID + " is null");
            return;
        }

        
        if(changesToTier.getS() != null){
            tierByID.setS(changesToTier.getA());
        }

        if(changesToTier.getA() != null){
            tierByID.setA(changesToTier.getA());
        }

        if(changesToTier.getB() != null){
            tierByID.setB(changesToTier.getA());
        }

        if(changesToTier.getC() != null){
            tierByID.setC(changesToTier.getA());
        }

        if(changesToTier.getD() != null){
            tierByID.setD(changesToTier.getA());
        }

        if(changesToTier.getF() != null){
            tierByID.setF(changesToTier.getA());
        }

        tierRepo.save(tierByID);
    }

    public void putTier(Integer id, Tier newTier){
        Tier currentTier = tierRepo.findById(id).orElse(null);

        currentTier.setS(newTier.getS());
        currentTier.setA(newTier.getA());
        currentTier.setB(newTier.getB());
        currentTier.setC(newTier.getC());
        currentTier.setD(newTier.getD());
        currentTier.setF(newTier.getF());

        tierRepo.save(currentTier);
    }

}
