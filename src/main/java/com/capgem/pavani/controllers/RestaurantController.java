package com.capgem.pavani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgem.pavani.entities.Rating;
import com.capgem.pavani.entities.Restaurant;
import com.capgem.pavani.services.RatingService;
import com.capgem.pavani.services.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private RatingService ratingService;

    // Retrieve a list of all restaurants
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);  // Return the list of restaurants
    }

    // Retrieve details of a specific restaurant
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);  // Return the restaurant details
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    // Update details of a specific restaurant
    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int restaurantId, @RequestBody Restaurant updatedRestaurant) {
        Restaurant updateddRestaurant = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
        return ResponseEntity.ok(updateddRestaurant);
    }

    // Delete a specific restaurant
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok("Restaurant Deleted Successfully.");
    }
    
    
    
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<List<Rating>> getReviewsByRestaurant(@PathVariable int restaurantId) {
        List<Rating> ratings = ratingService.getRatingsByRestaurant(restaurantId);
        return ResponseEntity.ok(ratings);
    }
    
    
    
    //There is no relation between delivery-addresses and restaurants in db;
//    @GetMapping("/{restaurantId}/delivery-areas")
//    public ResponseEntity<List<DeliveryAddress>> getDeliveryAreas(@PathVariable int restaurantId) {
//        List<DeliveryAddress> deliveryAddresses = deliveryAreaService.getDeliveryAreasByRestaurantId(restaurantId);
//        return ResponseEntity.ok(deliveryAddresses);
//    }
}

