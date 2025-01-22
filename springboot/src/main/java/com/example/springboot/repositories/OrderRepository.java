package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    //
}
