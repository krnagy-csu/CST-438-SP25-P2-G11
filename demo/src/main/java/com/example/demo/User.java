package com.example.demo;

// https://www.youtube.com/watch?v=gJ9DYC-jswo
// I used this video to help me understand records better 
// it is pretty short video < 15 minutes

public record User(Integer id, String username, String password){

    // when user is first created it will go through this constructor needing the password to be greater than 6 as we continue we can chnage it up
    // We can also encrpyt the password in this constructor
    public User {
        // we can check if the password is the correct size and other fields needed
        if (password.length() < 6){

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