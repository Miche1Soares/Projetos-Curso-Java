package com.example.springboot_mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import com.example.springboot_mongo.domain.Post;

//@Repository
public interface PostRepository extends MongoRepository<Post, String>{

    // query regex do mongodb
    //                           ?0 - diz pra pegar o primeiro parametro que vier no metodo, neste caso, o text
    //                                         i - ignorar maiusculas e minusculas
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);
    
    
    // query methods - este, por exemplo, busca automaticamente algum conteudo nos titulos dos posts, ignorando se nos posts há ou n letras maiúsculas
    List<Post> findByTitleContainingIgnoreCase(String text);


    // buscando um algo presente em algum titulo em um determinado intervalo de datas
    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
