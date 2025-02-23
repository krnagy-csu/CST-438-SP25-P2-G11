package com.example.demo;

// https://www.youtube.com/watch?v=gJ9DYC-jswo
// I used this video to help me understand records better 
// it is pretty short video < 15 minutes

public record User(Integer id, String username, String password){

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