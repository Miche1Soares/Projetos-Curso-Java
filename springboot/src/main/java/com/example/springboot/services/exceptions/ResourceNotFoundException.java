package com.example.springboot.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(Object id)
    {
        super("Ressource not found! Id = " + id);
    }

}
