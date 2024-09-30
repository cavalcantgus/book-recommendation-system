package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	private String title;

	@ManyToOne // Relacionamento bidirecional com Author
	@JoinColumn(name = "author_id", nullable = false) // Um objeto Author está associado a vários objetos Book
	private Author author;
	private String summary;
	private String isbn;
	private Date publicationDate;

	@JsonIgnore
	@OneToMany(mappedBy = "book") // Relacionamento bidirecional com Recommendation
	@Setter(AccessLevel.NONE)
	private Set<Recommendation> recommendation; // Um objeto Book está associado a vários Recommendation

	@JsonIgnore
	@OneToMany(mappedBy = "book") // Relacionamento bidirecional com Recommendation
	@Setter(AccessLevel.NONE)
	private Set<Rating> rating; // Um objeto Book está associado a vários Rating

	@ManyToOne // Relacionamento bidirecional com Genre
	@JoinColumn(name = "genre_id", nullable = false) // Um objeto Genre está associado a vários objetos Book
	private Genre genre;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	// Construtor parametrizado
	public Book(String title, String summary, Author author, String isbn, Date publicationDate, Genre genre) {
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.updatedAt = createdAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
		this.updatedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Timestamp(System.currentTimeMillis());
	}

}
