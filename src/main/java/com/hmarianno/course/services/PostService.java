package com.hmarianno.course.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmarianno.course.domain.Post;
import com.hmarianno.course.repository.PostRepository;
import com.hmarianno.course.services.exception.ObjectNotFoundException;

// serviço é a classe que conversa com a camada de acesso a 
// dados (interface repositório) - ver no início do material 
// de apoio o diagrama

@Service // avisa ao spring que essa classe é um serviço que pode ser 
		 // injetável em outras classes
public class PostService {

	@Autowired // injeção de dependência automática do Spring
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		// macete para a data máxima poder ser comparada com menor ou igual,
		// tem de ser a meia noite do próximo dia. 
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
