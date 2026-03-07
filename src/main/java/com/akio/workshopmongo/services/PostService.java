package com.akio.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akio.workshopmongo.domain.Post;
import com.akio.workshopmongo.repository.PostRepository;
import com.akio.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto not found!" + id));
	}

	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}

}
