package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.Category;
import com.example.springboot.repositories.CategoryRepository;

// para o autowired do Categoryresource funcionar
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll()
    {
        return repository.findAll();
    }

    public Category findById(Long id)
    {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

}
