package com.example.demo;

import com.example.domain.User;
import com.example.repos.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RESTApiTest {

	@Autowired
	UserRepository userRepository;

	@Test
	void createUser() throws JsonMappingException, JsonProcessingException {

		//given
		String JSON_INPUT = "{\"username\" : \"user\", \"password\" : \"user\", \"firstname\" : \"user\", \"lastname\" : \"user\", \"email\" : \"user@test.pl\"}";
		User newUser = new ObjectMapper().readValue(JSON_INPUT, User.class);

		//when
		//then
		RestAssured.given()
		.contentType("application/json")
		.body(newUser)
		.when().post("http://localhost:8080/api/v1/user/register").then()
		.statusCode(201);

	}


}
