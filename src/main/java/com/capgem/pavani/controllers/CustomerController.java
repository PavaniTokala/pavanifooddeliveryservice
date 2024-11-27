package com.capgem.pavani.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgem.pavani.entities.Customer;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    
    // Retrieve a list of all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Retrieve details of a specific customer
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    // Update details of a specific customer
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Delete a specific customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    // Retrieve all orders placed by a specific customer
    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable int customerId) {
        List<Order> orders = customerService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(orders);
    }

//    
//    @GetMapping("/{customerId}/favorites")
//    public ResponseEntity<List<FavoriteRestaurant>> getFavorites(@PathVariable int customerId) {
//        List<FavoriteRestaurant> favorites = favoriteRestaurantService.getFavoritesByCustomerId(customerId);
//        return ResponseEntity.ok(favorites);
//    }
//    
//    @PostMapping("/{customerId}/favorites")
//    public ResponseEntity<FavoriteRestaurant> addFavorite(@PathVariable int customerId, @RequestBody FavoriteRestaurant favoriteRestaurant) {
//        favoriteRestaurant.getCustomer().setCustomerId(customerId);
//        FavoriteRestaurant savedFavorite = favoriteRestaurantService.addFavoriteRestaurant(favoriteRestaurant);
//        return ResponseEntity.status(201).body(savedFavorite);
//    }
//
//    @DeleteMapping("/{customerId}/favorites/{restaurantId}")
//    public ResponseEntity<Void> removeFavorite(@PathVariable int customerId, @PathVariable int restaurantId) {
//        favoriteRestaurantService.removeFavoriteRestaurant(customerId, restaurantId);
//        return ResponseEntity.noContent().build();
//    }
}

