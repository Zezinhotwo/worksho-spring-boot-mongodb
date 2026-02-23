package com.akio.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akio.workshopmongo.config.Instantiation;
import com.akio.workshopmongo.domain.User;
import com.akio.workshopmongo.dto.UserDTO;
import com.akio.workshopmongo.repository.UserRepository;
import com.akio.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    private final Instantiation instantiation;

	@Autowired
	private UserRepository repo;

    UserService(Instantiation instantiation) {
        this.instantiation = instantiation;
    }

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto not found!" + id));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(),objDto.getEmail());
	}
	
}
