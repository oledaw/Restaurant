package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.domain.User;
import com.example.dto.UserLite;

public interface UserService {

	void save(User newUser);

	User findByUsername(String username);

	List<User> findAll();

	User findByEmail(String email);

	Optional<User> findById(Long userId);

	List<UserLite> findCustom();

	<T> List <T> findBy(Class<T> type);

	<T> T genericMethod(Long userId, Class<T> type);

}
