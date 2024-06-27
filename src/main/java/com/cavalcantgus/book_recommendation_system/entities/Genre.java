package com.cavalcantgus.book_recommendation_system.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Mapeada como entidade JPA
@Table(name = "genre")
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Geração automática de ID
	private Long id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "genre") // Relacionamento bidirecional com Book
	private Set<Book> book;		   // Um Book pode estar associado a somente um Genre
	private Timestamp created_at;
	private Timestamp updated_at;
	
	// Construtor padrão
	public Genre() {}
	
	// Construtor parametrizado
	public Genre(String name, String description) {
		this.name = name;
		this.description = description;
		this.created_at = new Timestamp(System.currentTimeMillis());
		this.updated_at = created_at;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setUpdated_at(new Timestamp(System.currentTimeMillis()));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		setUpdated_at(new Timestamp(System.currentTimeMillis()));
	}

	public Set<Book> getBook() {
		return book;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}	

}
