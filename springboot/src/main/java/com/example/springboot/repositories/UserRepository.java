package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    //
}
