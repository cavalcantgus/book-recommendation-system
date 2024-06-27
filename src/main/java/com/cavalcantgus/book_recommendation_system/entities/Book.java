package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Mapeada como entidade JPA
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Geração automática de ID
	private Long id;
	private String title;
	
	@ManyToOne // Relacionamento bidirecional com Author
	@JoinColumn(name = "author_id", nullable = false) // Um objeto Author está associado a vários objetos Book
	private Author author;
	private String summary;
	private String isbn;
	private Date publicationDate;
	
	@OneToMany(mappedBy = "book")  // Relacionamento bidirecional com Recommendation
	private Set<Recommendation> recommendation; // Um objeto Book está associado a vários Recommendation
	
	@OneToMany(mappedBy = "book") // Relacionamento bidirecional com Recommendation
	private Set<Rating> rating; // Um objeto Book está associado a vários Rating
	
	@ManyToOne // Relacionamento bidirecional com Genre
	@JoinColumn(name = "genre_id", nullable = false) // Um objeto Genre está associado a vários objetos Book
	private Genre genre;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	// Construtor padrão
	public Book() {}
	
	// Construtor parametrizado
	public Book(String title, String summary, Author author, String isbn, Date publicationDate, Genre genre) {
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = created_at;
	}
	
	// Getters & Setters
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
	
	public Date getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
	
	public Timestamp getCreatedAt() {
		return created_at;
	}
	
	public Timestamp getUpdatedAt() {
		return updated_at;
	}
	
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;	
	}
	
	public Set<Rating> getRating(){
		return rating;
	}
	
	public Set<Recommendation> getRecommendation(){
		return recommendation;
	}
	
}
