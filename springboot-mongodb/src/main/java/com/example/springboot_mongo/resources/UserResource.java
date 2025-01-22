package com.example.springboot_mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springboot_mongo.domain.Post;
import com.example.springboot_mongo.domain.User;
import com.example.springboot_mongo.dto.UserDTO;
import com.example.springboot_mongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll()
    {
        //User maria = new User("1", "Maria Brown", "maria@gmail.com");
        //User alex = new User("2", "Alex Green", "alex@gmail.com");

        List<User> list = service.findAll();

        // converte a lista em listaDTO
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }



    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id)
    {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }



    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO)
    {
        User obj = service.fromDTO(objDTO);
        obj = service.insert(obj);

        // pega o endereço do novo objeto inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        //                    retorna o codigo 201
        return ResponseEntity.created(uri).build(); 
    }



    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        service.delete(id);

        // resposta que nao retorna nada = codigo 204. Para isso o noContent
        return ResponseEntity.noContent().build();
    }



    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id)
    {
        User obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }



    @RequestMapping(value="/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id)
    {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }

}
