package com.cavalcantgus.book_recommendation_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcantgus.book_recommendation_system.entities.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

}
