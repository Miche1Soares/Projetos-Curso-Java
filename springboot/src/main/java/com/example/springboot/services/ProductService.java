package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.Product;
import com.example.springboot.repositories.ProductRepository;

// para o autowired do Productresource funcionar
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll()
    {
        return repository.findAll();
    }

    public Product findById(Long id)
    {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

}
