package com.cavalcantgus.book_recommendation_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcantgus.book_recommendation_system.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
