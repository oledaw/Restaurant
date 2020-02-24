package com.example.services;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import com.example.repos.CustomerOrderRepository;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class CustomerOrderServiceImp implements CustomerOrderService {

    private static Logger LOGGER = Logger.getLogger(UserServiceImp.class.getName());

    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderServiceImp(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public <T> List<T> findBy(Class<T> type) {
        return customerOrderRepository.findBy(type);
    }

    
    
}