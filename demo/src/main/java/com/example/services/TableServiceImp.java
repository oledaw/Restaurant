package com.example.services;

import java.util.Optional;

import com.example.domain.RestaurantTable;
import com.example.repos.TableRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImp implements TableService {

    TableRepository tableRepository;

    public TableServiceImp(TableRepository TableRepository) {
        this.tableRepository = TableRepository;
    }

    @Override
    public Optional<RestaurantTable> findById(Long restaurantId) {
        return tableRepository.findById(restaurantId);
    }

    @Override
    public ResponseEntity save(RestaurantTable restaurantTable) {
        tableRepository.save(restaurantTable);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

	@Override
	public ResponseEntity releseTable(long id) {
        Optional<RestaurantTable> isRestaurantTable = tableRepository.findById(id);
        if(isRestaurantTable.isPresent()){
            RestaurantTable restaurantTable = isRestaurantTable.get();
            restaurantTable.setOccupied(false);
            return save(restaurantTable);	

        }
        	
        return new ResponseEntity("Nie znaleziono stolika", HttpStatus.NOT_FOUND);
	}

	
    
}