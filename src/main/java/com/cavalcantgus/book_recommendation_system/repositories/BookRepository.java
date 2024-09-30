package com.cavalcantgus.book_recommendation_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcantgus.book_recommendation_system.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
