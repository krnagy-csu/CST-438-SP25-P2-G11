package com.example.demo.Service;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.demo.Tables.Tier;

import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;



@Service
public class TierEmbeddingService {
    public ZooModel<String, float[]> model;
    public Predictor<String, float[]> predictor;
    
    public void init() {
        Criteria<String, float[]> criteria = Criteria.builder().setTypes(String.class, float[].class).optModelUrls("https://huggingface.co/sentence-transformers/all-MiniLM-L6-v2/resolve/main").optEngine("PyTorch").build();
        try {
            model = criteria.loadModel();
            predictor = model.newPredictor();
        } catch (ModelNotFoundException | MalformedModelException | IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Failed to load embedder");
        }
    }

    public float[] generateEmbedding(Tier tier) {
        String tierText = "S: " + tier.getS() + "A: " + tier.getA() + "B: " + tier.getS() + "C: " + tier.getC() + "D: " + tier.getD() + "F: " + tier.getF();
        try {
            return predictor.predict(tierText);
        } catch (TranslateException e) {
            // TODO Auto-generated catch block
            System.err.println("Generate Embedding for Tier FAILED");
        }
        return null;
    }

    public Double similarity(float[] embedding, float[] embedding1){
        Double dotProduct = 0.0;
        Double norm1 = 0.0;
        Double norm2 = 0.0;
        
        for (int i = 0; i < embedding.length; i++) {
            dotProduct += embedding[i] * embedding1[i];
            norm1 += Math.pow(embedding[i], 2);
            norm2 += Math.pow(embedding1[i], 2);
        }
        
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

}
