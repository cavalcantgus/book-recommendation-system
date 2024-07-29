package com.cavalcantgus.book_recommendation_system.controllers;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.cavalcantgus.book_recommendation_system.entities.Genre;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.services.GenreService;
import com.cavalcantgus.book_recommendation_system.util.PatcherUpdater;

@RestController
@RequestMapping("/genres")
public class GenreController {

	private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

	@Autowired
	private GenreService service;

	@Autowired
	private PatcherUpdater patcher;

	@GetMapping
	public ResponseEntity<List<Genre>> findAll() {
		List<Genre> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Genre> findById(@PathVariable Long id) {
		Genre genre = service.findById(id);
		return ResponseEntity.ok().body(genre);
	}

	@PostMapping
	public ResponseEntity<Genre> insert(@RequestBody Genre genre) {
		service.insert(genre);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genre.getId()).toUri();
		return ResponseEntity.created(uri).body(genre);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody Genre genre) {
		genre = service.update(id, genre);
		return ResponseEntity.ok().body(genre);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Genre> patch(@PathVariable Long id, @RequestBody Genre genre) {

		try {
			Genre obj = service.findById(id);
			patcher.applyPartialUpdate(obj, genre);
			service.update(id, obj);
			return ResponseEntity.ok().body(obj);
		} catch (ResourceNotFoundException e) {
			logger.error("Genre with ID " + id + " not found", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Error while processing patch request for genre with ID " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Genre> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
