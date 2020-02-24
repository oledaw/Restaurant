package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.domain.CustomerOrder;
import com.example.domain.Manager;
import com.example.domain.OrderProduct;
import com.example.domain.Product;
import com.example.domain.Restaurant;
import com.example.domain.RestaurantTable;
import com.example.domain.User;
import com.example.dto.RestaurantManager;
import com.example.dto.RestaurantTableDto;
import com.example.repos.CustomerOrderRepository;
import com.example.repos.ProductRepository;
import com.example.repos.TableRepository;
import com.example.services.RestaurantService;
import com.example.services.TableService;
import com.example.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	TableRepository tablereposiotry;
	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	ProductRepository productRepository;

	@Autowired
	TableService tableService;


	@Test
	void contextLoads() {
	}

	@Test
	void createNewUser() throws JsonMappingException, JsonProcessingException {
		//given
		String JSON_INPUT = "{\"username\" : \"user\", \"password\" : \"user\",\"firstname\" : \"user\", \"lastname\" : \"user\", \"email\" : \"user@test.pl\"}";
		User newUser = new ObjectMapper().readValue(JSON_INPUT, User.class);

		//when
		userService.save(newUser);

		//then
		assertEquals(userService.findByUsername(newUser.getUsername()).getUsername(), 
			newUser.getUsername());
	}


	@Test
	void invalidEmail() throws JsonMappingException, JsonProcessingException {
		ValidatorFactory validatorFactory;
		Validator validator;
		//given
		validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();


		String JSON_INPUT = "{\"username\" : \"user\", \"password\" : \"user\", \"firstname\" : \"user\", \"lastname\" : \"user\", \"email\" : \"usertestppl\"}";
		User newUser = new ObjectMapper().readValue(JSON_INPUT, User.class);

		//when
		Set<ConstraintViolation<User>> violations = validator.validate(newUser);

		//then
		assertFalse(violations.isEmpty());
	}

	@Test
	void createNewManager() throws JsonMappingException, JsonProcessingException {
		//given
		String JSON_INPUT = "{\"username\" : \"manager\", \"password\" : \"manager\", \"firstname\" : \"manager\", \"lastname\" : \"manager\", \"email\" : \"manager@test.pl\"}";
		Manager newUser = new ObjectMapper().readValue(JSON_INPUT, Manager.class);

		//when
		userService.save(newUser);

		//then
		assertEquals(userService.findByUsername(newUser.getUsername()).getUsername(), newUser.getUsername());
	}

	@Test
	void createRestaurant() throws JsonMappingException, JsonProcessingException {
		//given
		String JSON_INPUT = "{\"name\" : \"restaurant\", \"type\" : \"restaurant\", \"password\" : \"restaurant\", \"email\" : \"restaurant@test.pl\"}";
		Restaurant restaurant = new ObjectMapper().readValue(JSON_INPUT, Restaurant.class);

		//when
		restaurantService.save(restaurant);

		//then
		assertEquals(restaurantService.findByEmail(restaurant.getEmail()).getEmail(), restaurant.getEmail());
	}

	@Test
	void addManagerToRestaurant() throws JsonMappingException, JsonProcessingException {
		//given
		userService.save(new User("user","user","user","user","user@test.pl"));
		userService.save(new Manager("manager", "manager", "manager", "manager", "manager@test.pl"));
		restaurantService.save(new Restaurant("restaurant", "restaurant", "restaurant", "restaurant@test.pl"));

		String JSON_INPUT = "{\"restaurantEmail\" : \"restaurant@test.pl\", \"managerEmail\" : \"manager@test.pl\"}";
		RestaurantManager restaurantManager = new ObjectMapper().readValue(JSON_INPUT, RestaurantManager.class);

		//when
		Restaurant restaurant = restaurantService.findByEmail(restaurantManager.getRestaurantEmail());
		User manager = userService.findByEmail(restaurantManager.getManagerEmail());

		restaurantService.addManager(restaurant, manager);

		assertEquals(restaurantService.findByEmail(restaurantManager.getRestaurantEmail()).getUsers().size(),1);
	}

	@Test
	void addTable() throws JsonMappingException, JsonProcessingException {
		//given
		userService.save(new Manager("manager", "manager", "manager", "manager", "manager@test.pl"));
		restaurantService.save(new Restaurant("restaurant", "restaurant", "restaurant", "restaurant@test.pl"));
		String JSON_INPUT = "{\"restaurantEmail\" : \"restaurant@test.pl\", \"managerEmail\" : \"manager@test.pl\"}";
		RestaurantManager restaurantManager = new ObjectMapper().readValue(JSON_INPUT, RestaurantManager.class);

		Restaurant restaurant = restaurantService.findByEmail(restaurantManager.getRestaurantEmail());
		RestaurantTable restaurantTable = new RestaurantTable(2,false);


		//when
		restaurantTable.setRestaurant(restaurant);
		tablereposiotry.save(restaurantTable);
		
		//then
		assertEquals(restaurantService.findByEmail(restaurantManager.getRestaurantEmail()).getRestaurantTables().size(),1);


	}

	@Test
	void addOrder() throws JsonMappingException, JsonProcessingException {
		userService.save(new User("user","user","user","user","user@test.pl"));
		restaurantService.save(new Restaurant("restaurant", "restaurant", "restaurant", "restaurant@test.pl"));
		tablereposiotry.save(new RestaurantTable(2,false));
		
		String JSON_INPUT = "{\"userId\" : \"1\", \"tableId\" : \"1\"}";
		RestaurantTableDto restaurantTableDto = new ObjectMapper().readValue(JSON_INPUT, RestaurantTableDto.class);
		Optional<RestaurantTable> restaurantTableOptional = tableService.findById(restaurantTableDto.getTableId());
		RestaurantTable restaurantTable = restaurantTableOptional.get();
		Optional<User> currnetUserOptional = userService.findById(restaurantTableDto.getUserId());
		User currnetUser = currnetUserOptional.get(); 

		String JSON_INPUT1 = "{\"restaurantEmail\" : \"restaurant@test.pl\", \"managerEmail\" : \"manager@test.pl\"}";
		RestaurantManager restaurantManager = new ObjectMapper().readValue(JSON_INPUT1, RestaurantManager.class);
		Restaurant restaurant = restaurantService.findByEmail(restaurantManager.getRestaurantEmail());
		restaurant.getProducts().add(new Product(122, "Kon"));
		restaurant.getProducts().add(new Product(112322, "Cos tam"));
		restaurantService.save(restaurant);




		//when
		CustomerOrder customerOrder = new CustomerOrder(currnetUser, restaurantTable);
		OrderProduct orderProduct = new OrderProduct(new Product(10, "Kaczka"), customerOrder, 2);
		customerOrder.getOrderProducts().add(orderProduct);

		customerOrderRepository.save(customerOrder);


		
		currnetUserOptional = userService.findById(restaurantTableDto.getUserId());
		currnetUser = currnetUserOptional.get(); 
		
		//then
		assertEquals(currnetUser.getOrders().size(), 1);
	}


	@Test
	void addProductToRestaurant() throws JsonMappingException, JsonProcessingException {
		//given
		restaurantService.save(new Restaurant("restaurant", "restaurant", "restaurant", "restaurant@test.pl"));
		String JSON_INPUT = "{\"restaurantEmail\" : \"restaurant@test.pl\", \"managerEmail\" : \"manager@test.pl\"}";
		RestaurantManager restaurantManager = new ObjectMapper().readValue(JSON_INPUT, RestaurantManager.class);

		//when
		Restaurant restaurant = restaurantService.findByEmail(restaurantManager.getRestaurantEmail());
		restaurant.getProducts().add(new Product(122, "Kon"));
		restaurant.getProducts().add(new Product(112322, "Cos tam"));
		restaurantService.save(restaurant);

		//then
		assertEquals(restaurantService.findByEmail(restaurantManager.getRestaurantEmail()).getProducts().size(),2);


	}


}
