package com.cavalcantgus.book_recommendation_system.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.Author;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository repository;

	public List<Author> findAll() {
		List<Author> list = repository.findAll();
		return list;
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
			e.getMessage();
		}
	}

	public Author update(Long id, Author author) {
		try {
			if (repository.existsById(id)) {
				Author authorTarget = repository.getReferenceById(id);
				updateData(author, authorTarget);
				return repository.save(authorTarget);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	private void updateData(Author author, Author authorTarget) {
		authorTarget.setName(author.getName());
		authorTarget.setDateOfBirth(author.getDateOfBirth());
		authorTarget.setDescription(author.getDescription());
	}
}
