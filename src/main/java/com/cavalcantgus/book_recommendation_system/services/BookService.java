package com.cavalcantgus.book_recommendation_system.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.Book;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public List<Book> findAll() {
		return repository.findAll();
	}

	public Book findById(Long id) {
		Optional<Book> book = repository.findById(id);
		return book.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Book insert(Book book) {
		return repository.save(book);
	}

	public void deleteById(Long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
	}

	public Book update(Long id, Book book) {
		try {
			if (repository.existsById(id)) {
				Book bookTarget = repository.getReferenceById(id);
				updateData(book, bookTarget);
				return repository.save(bookTarget);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	private void updateData(Book bookSource, Book bookTarget) {
		bookTarget.setAuthor(bookSource.getAuthor());
		bookTarget.setGenre(bookSource.getGenre());
		bookTarget.setTitle(bookSource.getTitle());
		bookTarget.setIsbn(bookSource.getIsbn());
		bookTarget.setPublicationDate(bookSource.getPublicationDate());
		bookTarget.setSummary(bookSource.getSummary());
		bookTarget.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
}
