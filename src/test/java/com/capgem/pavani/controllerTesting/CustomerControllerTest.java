package com.capgem.pavani.controllerTesting;

import com.capgem.pavani.controllers.CustomerController;
import com.capgem.pavani.entities.Customer;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customer;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        customers = new ArrayList<>();
        customers.add(customer);
    }

    @Test
    void testGetAllCustomers() {
        // Mocking the service layer
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Call the controller method
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getCustomerName());
    }

    @Test
    void testGetCustomerById() {
        // Mocking the service layer
        when(customerService.getCustomerById(1)).thenReturn(customer);

        // Call the controller method
        ResponseEntity<Customer> response = customerController.getCustomerById(1);

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getCustomerName());
    }

    @Test
    void testUpdateCustomer() {
        // Mocking the service layer
        Customer updatedCustomer = new Customer(1, "John Updated", "johnupdated@example.com", "0987654321", null, null);
        when(customerService.updateCustomer(1, updatedCustomer)).thenReturn(updatedCustomer);

        // Call the controller method
        ResponseEntity<Customer> response = customerController.updateCustomer(1, updatedCustomer);

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Updated", response.getBody().getCustomerName());
    }

    @Test
    void testDeleteCustomer() {
        // No return value for delete, just ensure no exception is thrown
        doNothing().when(customerService).deleteCustomer(1);

        // Call the controller method
        ResponseEntity<Void> response = customerController.deleteCustomer(1);

        // Assertions
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());  // No Content status
    }

    @Test
    void testGetOrdersByCustomer() {
        // Mocking orders for the customer
        List<Order> orders = new ArrayList<>();
        orders.add(new Order()); // Add mock orders as needed
        when(customerService.getOrdersByCustomer(1)).thenReturn(orders);

        // Call the controller method
        ResponseEntity<List<Order>> response = customerController.getOrdersByCustomer(1);

        // Assertions
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size()); // Assumes one order is returned
    }
}

