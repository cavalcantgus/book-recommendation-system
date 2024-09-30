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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(name = "author_name")
	private String name;
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "author") // Relacionamento bidirecional com Book. Um autor pode ter
	private Set<Book> books; // vários livros

	private Date dateOfBirth;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Construtor parametrizado
	public Author(String name, String description, Date dateOfBirth) {
		this.name = name;
		this.description = description;
		this.dateOfBirth = dateOfBirth;
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
