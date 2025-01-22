package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.User;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.services.exceptions.DatabaseException;
import com.example.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

// para o autowired do userresource funcionar
@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll()
    {
        return repository.findAll();
    }

    public User findById(Long id)
    {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


    // inserindo usuario
    public User insert(User obj)
    {
        return repository.save(obj);
    }


    // deletando um usuario
    public void delete(Long id)
    {
        try
        {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e)
        {
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new DatabaseException(e.getMessage());
        }
    }


    // atualizando um usuario
    public User update(Long id, User obj)
    {
        try 
        {
            //                       diferente do findById (que vai no banco de dados e traz o "objeto") o getReferenceById só prepara um objeto para realizar alterações e dps enviar ao database
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch(EntityNotFoundException e)
        {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) 
    {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
