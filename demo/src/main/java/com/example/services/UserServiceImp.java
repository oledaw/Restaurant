package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import com.example.domain.User;
import com.example.dto.UserLite;
import com.example.repos.RoleRepository;
import com.example.repos.UserRepository;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImp implements UserService {

    private static Logger LOGGER = Logger.getLogger(UserServiceImp.class.getName());

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User newUser) {
        LOGGER.info("User: " + newUser.getUsername() + " was created. New User Roles are: " + newUser.getRoles());
        userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        LOGGER.info("User was founded in database: " + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        LOGGER.info("User was founded in database: " + email);
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long userId) {
        // TODO Auto-generated method stub
        return userRepository.findById(userId);
    }

    @Override
    public List<UserLite> findCustom() {
        return userRepository.findByOrderById();
         
    }

    @Override
    public <T> List<T> findBy(Class<T> type) {
        
        return userRepository.findBy(type);
    }

    @Override
    public <T> T genericMethod(Long userId, Class<T> type) {
        return userRepository.findByIdOrderById(userId, type);
        
    }
    
}