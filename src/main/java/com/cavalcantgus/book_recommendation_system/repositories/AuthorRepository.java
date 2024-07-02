package com.cavalcantgus.book_recommendation_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcantgus.book_recommendation_system.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
