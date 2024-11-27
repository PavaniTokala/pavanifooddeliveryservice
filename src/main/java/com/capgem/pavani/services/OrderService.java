package com.capgem.pavani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgem.pavani.entities.DeliveryDriver;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.DeliveryDriverRepository;
import com.capgem.pavani.repositories.OrderRepository;

@Service
public class OrderService {
	
	 @Autowired
	    private OrderRepository orderRepository;
	 @Autowired
	    private DeliveryDriverRepository deliveryDriverRepository;
	     
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }
	    


	    public Order placeOrder(Order order) {
	        return orderRepository.save(order);
	    }
	    
	    public Order getOrderById(int orderId) {
	        Optional<Order> order = orderRepository.findById(orderId);
	        if (order.isPresent()) {
	            return order.get();
	        } else {
	            throw new ResourceNotFoundException("Order not found with id: " + orderId);
	        }
	    }
	    
	    public Order updateOrderStatus(int orderId, String status) {
	        Optional<Order> existingOrder = orderRepository.findById(orderId);
	        if (existingOrder.isPresent()) {
	            Order order = existingOrder.get();
	            order.setOrderStatus(status);
	            return orderRepository.save(order);
	        } else {
	            throw new ResourceNotFoundException("Order not found with id: " + orderId);
	        }
	    }
	    
	    public void cancelOrder(int orderId) {
	        Order order = getOrderById(orderId);
	        orderRepository.delete(order);
	    }
	    
	    public Order assignDriverToOrder(int orderId, int driverId) {
	        // Retrieve the order by ID
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
	        
	        // Retrieve the driver by ID
	        DeliveryDriver driver = deliveryDriverRepository.findById(driverId)
	                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));

	        // Assign the driver to the order
	        order.setDeliveryDriver(driver);

	        // Save the updated order
	        return orderRepository.save(order);
	    }
	    
}
