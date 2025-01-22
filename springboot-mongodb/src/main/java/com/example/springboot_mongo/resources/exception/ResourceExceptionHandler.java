// NAO RETORNAR CÓDIGO 500 SE HOUVER ERRO NA REQUISIÇÃO, E SIM O 404 DE NAO ENCONTRADO  

package com.example.springboot_mongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springboot_mongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

// responsavel por tratare possiveis erros nas requisicoes
@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
