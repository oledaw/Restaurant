package com.example.services;

import java.util.List;

public interface CustomerOrderService {


	<T> List <T> findBy(Class<T> type);

}
