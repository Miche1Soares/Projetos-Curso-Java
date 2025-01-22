package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    //
}
