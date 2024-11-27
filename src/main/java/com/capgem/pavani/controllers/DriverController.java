package com.capgem.pavani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgem.pavani.entities.DeliveryDriver;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    // Retrieve a list of all delivery drivers
    @GetMapping
    public ResponseEntity<List<DeliveryDriver>> getAllDrivers() {
        List<DeliveryDriver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }
    
    // Retrieve details of a specific delivery driver
    @GetMapping("/{driverId}")
    public ResponseEntity<DeliveryDriver> getDriverById(@PathVariable int driverId) {
        DeliveryDriver driver = driverService.getDriverById(driverId);
        return ResponseEntity.ok(driver);
    }

    // Assign a specific driver to an order
    @PutMapping("/orders/{orderId}/assignDriver/{driverId}")
    public ResponseEntity<Order> assignDriverToOrder(@PathVariable int orderId, @PathVariable int driverId) {
        Order updatedOrder = driverService.assignDriverToOrder(orderId, driverId);
        return ResponseEntity.ok(updatedOrder);
    }

    // Update the location of a specific driver
    
//    http://localhost:8080/api/drivers/1/location?location=agra
    @PutMapping("/{driverId}/location")
    public ResponseEntity<DeliveryDriver> updateDriverLocation(@PathVariable int driverId, @RequestParam String location) {
        DeliveryDriver updatedDriver = driverService.updateDriverLocation(driverId, location);
        return ResponseEntity.ok(updatedDriver);
    }

    // Retrieve all orders assigned to a specific delivery driver
    @GetMapping("/{driverId}/orders")
    public ResponseEntity<List<Order>> getOrdersAssignedToDriver(@PathVariable int driverId) {
        List<Order> orders = driverService.getOrdersAssignedToDriver(driverId);
        return ResponseEntity.ok(orders);
    }
}

