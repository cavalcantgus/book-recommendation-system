package com.cavalcantgus.book_recommendation_system.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cavalcantgus.book_recommendation_system.entities.User;
import com.cavalcantgus.book_recommendation_system.exceptions.ResourceNotFoundException;
import com.cavalcantgus.book_recommendation_system.repositories.UserRepository;
import com.cavalcantgus.book_recommendation_system.security.CustomPasswordEncoder;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private CustomPasswordEncoder encoder;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User user) {
		user.setPassword(encoder.passwordEnconder().encode(user.getPassword()));
		return repository.save(user);
	}

	public void deleteById(Long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
	}

	public User update(Long id, User user) {
		try {
			if (repository.existsById(id)) {
				User userTarget = repository.getReferenceById(id);
				updateData(user, userTarget);
				return repository.save(userTarget);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	private void updateData(User user, User userTarget) {
		userTarget.setUsername(user.getUsername());
		userTarget.setEmail(user.getEmail());
		userTarget.setPassword(user.getPassword());
		userTarget.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}
}
