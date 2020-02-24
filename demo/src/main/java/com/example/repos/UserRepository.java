package com.example.repos;

import java.util.List;
import com.example.domain.User;
import com.example.dto.UserLite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	Boolean existsUserByUsername(String username);

	User findByEmail(String email);

	<T> T findByIdOrderById(Long id, Class<T> type);


	List<UserLite> findByOrderById();

	<T> List <T> findBy(Class<T> type);

}
