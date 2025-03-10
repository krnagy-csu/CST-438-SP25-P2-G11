package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Tables.User;

//Extends JpaRepository<Item, ID type>
//JPA has the CRUD operations

 public interface UserRepo extends JpaRepository<User, Integer>{
    User findByUsername(String username);
 }
