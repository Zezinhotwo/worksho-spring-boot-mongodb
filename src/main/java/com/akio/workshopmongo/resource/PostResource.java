package com.akio.workshopmongo.resource;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akio.workshopmongo.domain.Post;
import com.akio.workshopmongo.resource.util.URL;
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

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullsearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		text = URL.decodeParam(text);

		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());

		List<Post> list = service.fullsearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}