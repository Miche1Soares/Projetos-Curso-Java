package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    //
}
