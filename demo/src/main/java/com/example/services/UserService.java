package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.domain.User;

public interface UserService {

	void save(User newUser);

	User findByUsername(String username);

	List<User> findAll();

	User findByEmail(String email);

	Optional<User> findById(Long userId);

}
