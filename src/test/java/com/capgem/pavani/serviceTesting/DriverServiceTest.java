package com.capgem.pavani.serviceTesting;

import com.capgem.pavani.entities.DeliveryDriver;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.DeliveryDriverRepository;
import com.capgem.pavani.repositories.OrderRepository;
import com.capgem.pavani.services.DriverService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverServiceTest {

    @Mock
    private DeliveryDriverRepository driverRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DriverService driverService;

    private DeliveryDriver driver;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data
        driver = new DeliveryDriver();
        driver.setDriverId(1);
        driver.setDriverName("John Doe");

        order = new Order();
        order.setOrderId(100);
    }

    @Test
    void testGetAllDrivers() {
        // Mocking repository response
        when(driverRepository.findAll()).thenReturn(Arrays.asList(driver));

        // Call service method
        List<DeliveryDriver> result = driverService.getAllDrivers();

        // Verifying the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getDriverName());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void testGetDriverById_Success() {
        // Mocking repository response
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        // Call service method
        DeliveryDriver result = driverService.getDriverById(1);

        // Verifying the result
        assertNotNull(result);
        assertEquals("John Doe", result.getDriverName());
        verify(driverRepository, times(1)).findById(1);
    }

    @Test
    void testGetDriverById_NotFound() {
        // Mocking repository response for non-existent driver
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        // Verifying the exception
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            driverService.getDriverById(1);
        });
        assertEquals("Delivery driver not found with id: 1", exception.getMessage());
        verify(driverRepository, times(1)).findById(1);
    }
//
//    @Test
//    void testAssignDriverToOrder() {
//        // Mocking repository responses
//        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
//        when(orderRepository.findById(100)).thenReturn(Optional.of(order));
//
//        // Call service method
//        Order updatedOrder = driverService.assignDriverToOrder(100, 1);
//
//        // Verifying the result
//        assertNotNull(updatedOrder);
//        assertEquals(driver, updatedOrder.getDeliveryDriver());
//        verify(orderRepository, times(1)).findById(100);
//        verify(orderRepository, times(1)).save(updatedOrder);
//    }

    @Test
    void testAssignDriverToOrder_OrderNotFound() {
        // Mocking repository responses
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
        when(orderRepository.findById(100)).thenReturn(Optional.empty());

        // Verifying exception
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            driverService.assignDriverToOrder(100, 1);
        });
        assertEquals("Order not found", exception.getMessage());
        verify(orderRepository, times(1)).findById(100);
    }

//    @Test
//    void testUpdateDriverLocation() {
//        // Mocking repository response
//        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
//
//        // Call service method
//        DeliveryDriver updatedDriver = driverService.updateDriverLocation(1, "New Location");
//
//        // Verifying the result
//        assertNotNull(updatedDriver);
//        assertEquals("New Location", updatedDriver.getDriverLocation());
//        verify(driverRepository, times(1)).findById(1);
//        verify(driverRepository, times(1)).save(updatedDriver);
//    }

    @Test
    void testUpdateDriverLocation_DriverNotFound() {
        // Mocking repository response for non-existent driver
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        // Verifying exception
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            driverService.updateDriverLocation(1, "New Location");
        });
        assertEquals("Driver not found with id: 1", exception.getMessage());
        verify(driverRepository, times(1)).findById(1);
    }

    @Test
    void testGetOrdersAssignedToDriver() {
        // Mocking repository response
        when(orderRepository.findByDeliveryDriver_DriverId(1)).thenReturn(Arrays.asList(order));

        // Call service method
        List<Order> result = driverService.getOrdersAssignedToDriver(1);

        // Verifying the result
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByDeliveryDriver_DriverId(1);
    }
}

