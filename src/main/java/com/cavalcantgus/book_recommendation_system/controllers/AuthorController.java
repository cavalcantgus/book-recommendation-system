package com.cavalcantgus.book_recommendation_system.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cavalcantgus.book_recommendation_system.entities.Author;
import com.cavalcantgus.book_recommendation_system.services.AuthorService;
import com.cavalcantgus.book_recommendation_system.util.PatcherUpdater;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorService service;

	@Autowired
	private PatcherUpdater patcher;

	@GetMapping
	public ResponseEntity<List<Author>> findAll() {
		List<Author> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> findByIde(@PathVariable Long id) {
		Author author = service.findById(id);
		return ResponseEntity.ok().body(author);
	}

	@PostMapping
	public ResponseEntity<Author> insert(@RequestBody Author obj) {
		Author author = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getId()).toUri();
		return ResponseEntity.created(uri).body(author);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {
		author = service.update(id, author);
		return ResponseEntity.ok().body(author);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Author> patch(@PathVariable Long id, @RequestBody Author author) {
		Author obj = service.findById(id);

		try {
			patcher.applyPartialUpdate(obj, author);
			service.update(id, obj);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
