package com.example.demo;

import java.util.Optional;

import com.example.domain.CustomerOrder;
import com.example.domain.Manager;
import com.example.domain.OrderProduct;
import com.example.domain.Product;
import com.example.domain.Restaurant;
import com.example.domain.RestaurantTable;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.Role.Type;
import com.example.dto.RestaurantManager;
import com.example.dto.RestaurantTableDto;
import com.example.repos.CustomerOrderRepository;
import com.example.repos.OrderProductRepository;
import com.example.repos.ProductRepository;
import com.example.repos.RoleRepository;
import com.example.repos.TableRepository;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner{
    UserService userService;
    RoleRepository roleRepository;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    TableRepository tablereposiotry;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderProductRepository orderProductRepository;

    


    public BootStrap(final UserService userService, final RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        final Type[] type = Type.values();
        for (final Type t:type){
            roleRepository.save(new Role(t));
        }


        userService.save(new User("Customer","user","user","user","user@test.pl"));
        userService.save(new Manager("manager", "manager", "manager", "manager", "manager@test.pl"));
		restaurantService.save(new Restaurant("restaurant", "restaurant", "restaurant", "restaurant@test.pl"));
		tablereposiotry.save(new RestaurantTable(2,false));
		
		String JSON_INPUT = "{\"userId\" : \"1\", \"tableId\" : \"1\"}";
		RestaurantTableDto restaurantTableDto = new ObjectMapper().readValue(JSON_INPUT, RestaurantTableDto.class);
		Optional<RestaurantTable> restaurantTableOptional = tablereposiotry.findById(restaurantTableDto.getTableId());
        RestaurantTable restaurantTable = restaurantTableOptional.get();
        Restaurant restaurant = restaurantService.findByEmail("restaurant@test.pl");
        restaurant.getRestaurantTables().add(restaurantTable);
        restaurantService.save(restaurant);
        restaurantTable.setRestaurant(restaurantService.findByEmail("restaurant@test.pl"));
        tablereposiotry.save(restaurantTable);
		Optional<User> currnetUserOptional = userService.findById(restaurantTableDto.getUserId());
		User currnetUser = currnetUserOptional.get(); 

		String JSON_INPUT1 = "{\"restaurantEmail\" : \"restaurant@test.pl\", \"managerEmail\" : \"manager@test.pl\"}";
		RestaurantManager restaurantManager = new ObjectMapper().readValue(JSON_INPUT1, RestaurantManager.class);
		restaurantService.findByEmail(restaurantManager.getRestaurantEmail());
		restaurant.getProducts().add(new Product(122, "Kon"));
		restaurant.getProducts().add(new Product(112322, "Cos tam"));
		restaurantService.save(restaurant);




		//when
		CustomerOrder customerOrder = new CustomerOrder(currnetUser, restaurantTable);
        OrderProduct orderProduct = new OrderProduct(productRepository.findByName("Kon"), customerOrder, 2);
        customerOrder.getOrderProducts().add(orderProduct);
        customerOrder.addToTotal();
        customerOrder.checked();
        customerOrder.setRestaurant(restaurant);

		customerOrderRepository.save(customerOrder);


		
		currnetUserOptional = userService.findById(restaurantTableDto.getUserId());
		currnetUser = currnetUserOptional.get(); 
    }


}