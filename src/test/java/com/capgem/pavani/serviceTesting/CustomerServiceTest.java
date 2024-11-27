package com.capgem.pavani.serviceTesting;

import com.capgem.pavani.entities.Customer;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.CustomerRepository;
import com.capgem.pavani.repositories.OrderRepository;
import com.capgem.pavani.services.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

 
public class CustomerServiceTest {
 
    @InjectMocks
    private CustomerService customerService;
 
    @Mock
    private CustomerRepository customerRepository;
 
    @Mock
    private OrderRepository orderRepository;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testGetAllCustomers() {
        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        when(customerRepository.findAll()).thenReturn(java.util.Arrays.asList(customer));
 
        assertEquals(1, customerService.getAllCustomers().size());
        verify(customerRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetCustomerById_Success() {
        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
 
        Customer foundCustomer = customerService.getCustomerById(1);
        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getCustomerName());
        verify(customerRepository, times(1)).findById(1);
    }
 
    @Test
    public void testGetCustomerById_NotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(1));
    }
 
    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
 
        Customer createdCustomer = customerService.createCustomer(customer);
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getCustomerName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
 
    @Test
    public void testDeleteCustomer_Success() {
        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);
 
        customerService.deleteCustomer(1);
        verify(customerRepository, times(1)).delete(customer);
    }
 
    @Test
    public void testDeleteCustomer_NotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(RuntimeException.class, () -> customerService.deleteCustomer(1));
    }
 
    @Test
    public void testGetOrdersByCustomer() {
        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
        Order order = new Order();  // Assume valid order object
        when(orderRepository.findByCustomer_CustomerId(1)).thenReturn(java.util.Arrays.asList(order));
 
        assertEquals(1, customerService.getOrdersByCustomer(1).size());
        verify(orderRepository, times(1)).findByCustomer_CustomerId(1);
    }
}
 