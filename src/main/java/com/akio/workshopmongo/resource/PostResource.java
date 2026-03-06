package com.akio.workshopmongo.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akio.workshopmongo.domain.Post;
import com.akio.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	private final PostService service;

	public PostResource(PostService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public Post findById(@PathVariable String id) {
		return service.findById(id);
	}
}