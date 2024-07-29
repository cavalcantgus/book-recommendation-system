package com.cavalcantgus.book_recommendation_system.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.Genre;
import com.cavalcantgus.book_recommendation_system.exceptions.DatabaseException;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.GenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreService {

	private final static Logger logger = LoggerFactory.getLogger(GenreService.class);

	private final GenreRepository repository;

	public List<Genre> findAll() {
		return repository.findAll();
	}

	public Genre findById(Long id) {
		Optional<Genre> genre = repository.findById(id);
		return genre.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Genre insert(Genre genre) {
		return repository.save(genre);
	}

	public void deleteById(Long id) {

		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			logger.error("Data integrity violation while deleting genre with ID " + id, e);
			throw new DatabaseException("Could not delete genre with ID " + id + ". An unexpected error occurred.");
		}
	}

	public Genre update(Long id, Genre genre) {

		try {
			repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

			Genre updatedGenre = updateData(id, genre);
			return repository.save(updatedGenre);
		} catch (ResourceNotFoundException e) {
			logger.error("Error while updating genre with ID " + id, e);
			throw e;
		}
	}

	private Genre updateData(Long id, Genre genre) {
		Genre genreTarget = repository.getReferenceById(id);

		genreTarget.setName(genre.getName());
		genreTarget.setDescription(genre.getDescription());

		return genreTarget;
	}
}
