package com.cavalcantgus.book_recommendation_system.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.Rating;
import com.cavalcantgus.book_recommendation_system.exceptions.DatabaseException;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.RatingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {

	private final static Logger logger = LoggerFactory.getLogger(RatingService.class);

	private final RatingRepository repository;

	public List<Rating> findAll() {
		return repository.findAll();
	}

	public Rating findById(Long id) {
		Optional<Rating> rating = repository.findById(id);
		return rating.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Set<Rating> findByBookId(Long id) {
		return repository.findByBookId(id);
	}

	public Rating insert(Rating rating) {
		return repository.save(rating);
	}

	public void deleteById(Long id) {

		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			logger.error("Data integrity violation while deleting rating with ID " + id, e);
			throw new DatabaseException("Could not delete rating with ID " + id + ". An unexpected error occurred.");
		}
	}

	public Rating update(Long id, Rating rating) {

		try {
			repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

			Rating updatedRating = updateData(id, rating);
			return repository.save(updatedRating);
		} catch (ResourceNotFoundException e) {
			logger.error("Error while updating rating with ID " + id, e);
			throw e;
		}
	}

	private Rating updateData(Long id, Rating rating) {
		Rating ratingTarget = repository.getReferenceById(id);

		ratingTarget.setRating(rating.getRating());
		ratingTarget.setReview(rating.getReview());

		return ratingTarget;
	}
}
