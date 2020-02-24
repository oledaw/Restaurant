package com.example.repos;

import java.util.List;

import com.example.domain.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	Restaurant findByEmail(String email);

	<T> List<T> findBy(Class<T> type);

}
