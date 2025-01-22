package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    //
}
