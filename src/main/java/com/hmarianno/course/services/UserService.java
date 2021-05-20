package com.hmarianno.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmarianno.course.domain.User;
import com.hmarianno.course.dto.UserDTO;
import com.hmarianno.course.repository.UserRepository;
import com.hmarianno.course.services.exception.ObjectNotFoundException;

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
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		// tem que buscar o obj no BD, alterar esse obj com os novos dados e só 
		// aí alterar na base
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
