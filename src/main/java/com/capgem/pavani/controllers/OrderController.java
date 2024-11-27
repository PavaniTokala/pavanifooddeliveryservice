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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Place a new order
//    JsonBodyForBelow
//    {
//    	"orderId" : 9809,
//    	  "orderDate": "2024-11-27T08:00:00",
//    	  "orderStatus": "Pending",
//    	  "customer": {
//    	    "customerId": 3
//    	  }
//    	}
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order createdOrder = orderService.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Retrieve details of a specific order
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    // Update the status of a specific order
    
    // it fetches the status from url, 
//    http://localhost:8080/api/orders/9999/status?status=completed
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable int orderId, @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // Cancel a specific order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable int orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order Deleted successfully.");
    }
    
    @PutMapping("/{orderId}/assignDriver/{driverId}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable int orderId, @PathVariable int driverId) {
        orderService.assignDriverToOrder(orderId, driverId);
        return ResponseEntity.ok("Driver is Successfully Assigned with id :"+driverId);
    }

}

