package com.example.demo.Controllers;

import java.util.List;
<<<<<<< Updated upstream

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
=======
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
>>>>>>> Stashed changes

import com.example.demo.Service.TierEmbeddingService;
import com.example.demo.Service.TierService;
import com.example.demo.Tables.Tier;
import com.example.demo.Tables.Subject;
import com.example.demo.Tables.SubjectEntry;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tier")
public class TierController {
    @Autowired
    private TierEmbeddingService embeddingService;

    @Autowired
    private TierService tierService;

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
    @GetMapping("/subject/{subject}")
    public List<Tier> getTiersBySubject(@PathVariable String subject){
        return tierService.getTiersBySubject(subject);
    }

    @GetMapping("/compare")
    public String compareTiers(@RequestParam Integer id, @RequestParam String subject){
        String percentage = ""; 
        List<Tier> tiers = tierService.getTiersBySubject(subject);
        Integer randNum = (int) Math.rint(Math.random() * (10 - 0));
        Tier randomTier = tiers.get(randNum);
        float[] embedding = embeddingService.generateEmbedding(tierService.getTier(id));
        float[] embedding1 = embeddingService.generateEmbedding(randomTier);
        Double similarity = embeddingService.similarity(embedding, embedding1);
        similarity = similarity * 100.0;
        percentage = String.format("%.2f", similarity).toString();
        return percentage;
    }
    // @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
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

    @GetMapping("/getByUserId/{userId}")
    public List<Tier> getByUserId(@PathVariable Integer userId){
        return tierService.getTierByUser(userId);
    }

    @GetMapping("/getSubjects")
    public List<Subject> getAllSubjects() {
        return tierService.getAllSubjects();
    }

    // Get all entries for a subject
    @GetMapping("/{subjectId}/entries")
    public List<SubjectEntry> getEntriesForSubject(@PathVariable Integer subjectId) {
        return tierService.getEntriesBySubjectId(subjectId);
    }

}
