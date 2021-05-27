package com.hmarianno.course.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmarianno.course.domain.Post;
import com.hmarianno.course.resources.util.URL;
import com.hmarianno.course.services.PostService;

// o controlador rest é a camada mais externa, é a interface com o 
// usuário e acessa a camada de serviço que, por sua vez, acessa a
// interface com o repositório de dados


@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired // do mesmo jeito que injetou o Repository lá no service, 
			   /// injetar aqui o serviço para buscar os usuários
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text",    defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		
		text     = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L)); // data inicial do java
		Date max = URL.convertDate(maxDate, new Date());   // data atual
		
		List<Post> list = service.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(list);
	}

}
