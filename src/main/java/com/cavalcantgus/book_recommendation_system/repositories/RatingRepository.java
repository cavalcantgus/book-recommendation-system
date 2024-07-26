package com.cavalcantgus.book_recommendation_system.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcantgus.book_recommendation_system.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	Set<Rating> findByBookId(Long id);
}
