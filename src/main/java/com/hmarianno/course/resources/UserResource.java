package com.hmarianno.course.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hmarianno.course.domain.User;
import com.hmarianno.course.dto.UserDTO;
import com.hmarianno.course.services.UserService;

// o controlador rest é a camada mais externa, é a interface com o 
// usuário e acessa a camada de serviço que, por sua vez, acessa a
// interface com o repositório de dados


@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired // do mesmo jeito que injetou o Repository lá no service, 
			   /// injetar aqui o serviço para buscar os usuários
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
