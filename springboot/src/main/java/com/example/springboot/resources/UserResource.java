package com.example.springboot.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springboot.entities.User;
import com.example.springboot.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;
    
    @GetMapping
    public ResponseEntity<List<User>> findAll()
    {
        List<User> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    //                                  indica q id é um parametro passado na url
    public ResponseEntity<User> findById(@PathVariable Long id)
    {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    // inserindo usuario
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj)
    {
        obj = service.insert(obj);

        // define o URI dos dados informados
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        //                    cria uma resposta tipo 201 (criou um novo recurso), mas é necessário informar oq foi criado como um URI
        return ResponseEntity.created(uri).body(obj);
    }


    // deletando um usuario
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        service.delete(id);
        //                    retorna uma resposta vazia, do código 204 (resposta sem conteudo)
        return ResponseEntity.noContent().build();
    }


    // atualizando usuario
    @PutMapping(value="/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj)
    {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);        
    }

}
