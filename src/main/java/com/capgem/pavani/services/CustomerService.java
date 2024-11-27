package com.capgem.pavani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.pavani.entities.Customer;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.CustomerRepository;
import com.capgem.pavani.repositories.OrderRepository;
import com.capgem.pavani.repositories.RatingRepository;


@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;  // Add this for fetching orders

   

   
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }
    
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setCustomerName(updatedCustomer.getCustomerName());
            customer.setCustomerEmail(updatedCustomer.getCustomerEmail());
            customer.setCustomerPhone(updatedCustomer.getCustomerPhone());
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }
    
 // Retrieve all orders placed by a specific customer
    public List<Order> getOrdersByCustomer(int customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId);
    }

    // Retrieve all reviews submitted by a specific customer
//    public List<Rating> getReviewsByCustomer(int customerId) {
//        return ratingRepository.findByCustomer_CustomerId(customerId);
//    }

    // Add a restaurant to a customer's favorites
//    public FavoriteRestaurant addRestaurantToFavorites(int customerId, int restaurantId) {
//        Customer customer = getCustomerById(customerId);
//        Restaurant restaurant = restaurantRepository.findById(restaurantId)
//                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
//        FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant(customer, restaurant);
//        return favoriteRestaurantRepository.save(favoriteRestaurant);
//    }
//
//    // Remove a restaurant from a customer's favorites
//    public void removeRestaurantFromFavorites(int customerId, int restaurantId) {
//        FavoriteRestaurant favoriteRestaurant = favoriteRestaurantRepository.findByCustomerIdAndRestaurantId(customerId, restaurantId)
//                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found"));
//        favoriteRestaurantRepository.delete(favoriteRestaurant);
//    }
    
    
}
