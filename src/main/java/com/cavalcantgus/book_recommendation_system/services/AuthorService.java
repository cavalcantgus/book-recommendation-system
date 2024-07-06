package com.cavalcantgus.book_recommendation_system.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.Author;
import com.cavalcantgus.book_recommendation_system.exceptions.DatabaseException;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	private AuthorRepository repository;

	public List<Author> findAll() {
		return repository.findAll();

	}

	public Author findById(Long id) {
		Optional<Author> author = repository.findById(id);
		return author.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Author insert(Author author) {
		return repository.save(author);
	}

	public void delete(Long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			logger.error("Data integrity violation while deleting author with ID " + id, e);
			throw new DatabaseException("Could not delete author with ID " + id + ". An unexpected error occurred.");
		}
	}

	public Author update(Long id, Author author) {
		try {
			if (repository.existsById(id)) {
				Author updatedAuthor = updateData(id, author);
				return repository.save(updatedAuthor);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (EntityNotFoundException e) {
			logger.error("Error while updating author with ID " + id, e);
			throw new ResourceNotFoundException(id);
		}
	}

	private Author updateData(Long id, Author author) {
		Author authorTarget = repository.getReferenceById(id);

		authorTarget.setName(author.getName());
		authorTarget.setDescription(author.getDescription());
		authorTarget.setDateOfBirth(author.getDateOfBirth());

		return authorTarget;
	}
}
