package com.example.springboot_mongo.resources;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_mongo.domain.Post;
import com.example.springboot_mongo.resources.util.URL;
import com.example.springboot_mongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id)
    {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/titlesearch", method = RequestMethod.GET)

    //                                              busca o valor do text no endereço /titlesearch?text=... , e seta um valor em branco por padrao
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text)
    {
        //     decodifica a string do padrao de link (exemplo: bom%20dia -> "bom dia")
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }



    @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) 
    {
		text = URL.decodeParam(text);
        //                        se a conversão der errado, a data minima receberá (0L) o menor valor da biblioteca Date
		Date min = URL.convertDate(minDate, new Date(0L));
        //                        se a conversão der errado, a data maxima receberá a data atual
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
        
		return ResponseEntity.ok().body(list);
	}

}
