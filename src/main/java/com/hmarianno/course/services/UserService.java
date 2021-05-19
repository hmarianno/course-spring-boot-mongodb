package com.hmarianno.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmarianno.course.domain.User;
import com.hmarianno.course.repository.UserRepository;

// serviço é a classe que conversa com a camada de acesso a 
// dados (interface repositório) - ver no início do material 
// de apoio o diagrama

@Service // avisa ao spring que essa classe é um serviço que pode ser 
		 // injetável em outras classes
public class UserService {

	@Autowired // injeção de dependência automática do Spring
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll(); // retorna uma lista de usuários
	}
}
