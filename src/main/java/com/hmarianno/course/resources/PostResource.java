package com.hmarianno.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hmarianno.course.domain.Post;
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
	
}
