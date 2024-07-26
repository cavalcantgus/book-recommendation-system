package com.cavalcantgus.book_recommendation_system.controllers;

import java.net.URI;
import java.util.List;
import java.util.Set;

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

import com.cavalcantgus.book_recommendation_system.entities.Rating;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.services.RatingService;
import com.cavalcantgus.book_recommendation_system.util.PatcherUpdater;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	private final static Logger logger = LoggerFactory.getLogger(RatingController.class);

	@Autowired
	private RatingService service;

	@Autowired
	private PatcherUpdater patcher;

	@GetMapping
	public ResponseEntity<List<Rating>> findAll() {
		List<Rating> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rating> findById(@PathVariable Long id) {
		Rating rating = service.findById(id);
		return ResponseEntity.ok().body(rating);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Set<Rating>> findByBookId(@PathVariable Long id) {
		Set<Rating> rating = service.findByBookId(id);
		return ResponseEntity.ok().body(rating);
	}

	@PostMapping
	public ResponseEntity<Rating> insert(@RequestBody Rating rating) {
		service.insert(rating);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
		return ResponseEntity.created(uri).body(rating);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Rating> update(@PathVariable Long id, @RequestBody Rating rating) {
		rating = service.update(id, rating);
		return ResponseEntity.ok().body(rating);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Rating> patch(@PathVariable Long id, @RequestBody Rating rating) {

		try {
			Rating obj = service.findById(id);
			patcher.applyPartialUpdate(obj, rating);
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
	public ResponseEntity<Rating> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
