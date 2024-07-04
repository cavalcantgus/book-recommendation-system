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

import com.cavalcantgus.book_recommendation_system.entities.Book;
import com.cavalcantgus.book_recommendation_system.services.BookService;
import com.cavalcantgus.book_recommendation_system.util.PatcherUpdater;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService service;

	@Autowired
	private PatcherUpdater patcher;

	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		List<Book> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Book book = service.findById(id);
		return ResponseEntity.ok().body(book);
	}

	@PostMapping
	public ResponseEntity<Book> insert(@RequestBody Book obj) {
		Book book = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).body(book);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
		book = service.update(id, book);
		return ResponseEntity.ok().body(book);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Book> patch(@PathVariable Long id, @RequestBody Book book) {
		Book obj = service.findById(id);

		try {
			patcher.applyPartialUpdate(obj, book);
			service.update(id, obj);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
