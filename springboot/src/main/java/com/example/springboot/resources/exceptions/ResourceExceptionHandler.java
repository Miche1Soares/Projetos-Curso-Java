package com.example.springboot.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springboot.services.exceptions.DatabaseException;
import com.example.springboot.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

// intercepta as exceções para que seja possivel executar um tratamento
@ControllerAdvice
public class ResourceExceptionHandler {
    
    // pra que seja possivel interceptar a exceção do tipo (daqui)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)
    {
        String error = "Resource not found!";
        // retorna o erro 404 
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request)
    {
        String error = "Database error";
        // retorna o erro 400
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
