package com.capgem.pavani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.entities.Restaurant;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.MenuItemRepository;
import com.capgem.pavani.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
	
	public Restaurant getRestaurantById(int restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
        }
    }
	
	public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
	
	 public Restaurant updateRestaurant(int restaurantId, Restaurant updatedRestaurant) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        if (restaurant.isPresent()) {
	            Restaurant existingRestaurant = restaurant.get();
	            existingRestaurant.setRestaurantId(updatedRestaurant.getRestaurantId());
	            existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
	            existingRestaurant.setRestaurantAddress(updatedRestaurant.getRestaurantAddress());
	            existingRestaurant.setRestaurantPhone(updatedRestaurant.getRestaurantPhone());
	            return restaurantRepository.save(existingRestaurant); 
	        } else {
	            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
	        }
	    }
	 
	 public void deleteRestaurant(int restaurantId) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	        if (restaurant.isPresent()) {
	            restaurantRepository.delete(restaurant.get());
	        } else {
	            throw new RuntimeException("Restaurant not found with id: " + restaurantId);
	        }
	    }
	 
//	 public List<MenuItem> getAllMenuItemsForRestaurant(int restaurantId) {
//	        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
//	        if (restaurant.isPresent()) {
//	            return menuItemRepository.findByRestaurants(restaurant.get());  // Return menu items for the restaurant
//	        } else {
//	            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
//	        }
//	    }
	 
	

}
