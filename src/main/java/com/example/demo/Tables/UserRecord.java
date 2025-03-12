package com.example.demo.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// https://www.youtube.com/watch?v=gJ9DYC-jswo
// I used this video to help me understand records better 
// it is pretty short video < 15 minutes

@Entity
public record UserRecord(@Id Integer id, String username, String password){

    // when user is first created it will go through this constructor needing the password to be greater than 6 as we continue we can chnage it up
    // We can also encrpyt the password in this constructor
    public void checkPassword(String newPassword){
        if(newPassword.length() >= 6){
            
        }
    } 
     
    void encryptPassword(String password){
        //when we add bcrypt or another encpryting library encrypt in this fuction
    }

    boolean isPasswordValid(String password){
        //compare entered password with password stored into the database
        return true;
    }

}