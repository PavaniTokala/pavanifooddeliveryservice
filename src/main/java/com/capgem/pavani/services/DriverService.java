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
public class DriverService {
	
	@Autowired
    private DeliveryDriverRepository driverRepository;
    @Autowired
    private OrderRepository orderRepository;
    
    public List<DeliveryDriver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Get a delivery driver by ID
    public DeliveryDriver getDriverById(int driverId) {
        Optional<DeliveryDriver> driver = driverRepository.findById(driverId);
        if (driver.isPresent()) {
            return driver.get();  // Return the driver if found
        } else {
            throw new ResourceNotFoundException("Delivery driver not found with id: " + driverId);
        }
    }
    public Order assignDriverToOrder(int orderId, int driverId) {
        DeliveryDriver driver = getDriverById(driverId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setDeliveryDriver(driver);
        return orderRepository.save(order);
    }
    
    public DeliveryDriver updateDriverLocation(int driverId, String location) {
    DeliveryDriver driver = driverRepository.findById(driverId)
            .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));
    driver.setDriverLocation(location);  // Assuming driver has a 'location' property
    return driverRepository.save(driver);  // Save the updated driver
}
    
 // Retrieve all orders assigned to a specific driver
    public List<Order> getOrdersAssignedToDriver(int driverId) {
        return orderRepository.findByDeliveryDriver_DriverId(driverId);
    }

}
