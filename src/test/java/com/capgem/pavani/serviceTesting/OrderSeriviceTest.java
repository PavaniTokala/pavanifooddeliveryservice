package com.capgem.pavani.serviceTesting;

import com.capgem.pavani.entities.Order;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.OrderRepository;
import com.capgem.pavani.services.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
class OrderServiceTest {
 
    @Mock
    private OrderRepository orderRepository;
 
    @InjectMocks
    private OrderService orderService;
 
    private Order order;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setOrderId(1);
        order.setOrderStatus("Pending");
    }
 
    @Test
    void testPlaceOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);
 
        Order createdOrder = orderService.placeOrder(order);
 
        assertNotNull(createdOrder);
        assertEquals(1, createdOrder.getOrderId());
        assertEquals("Pending", createdOrder.getOrderStatus());
 
        verify(orderRepository, times(1)).save(any(Order.class));
    }
 
    @Test
    void testGetOrderById() {
        when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(order));
 
        Order foundOrder = orderService.getOrderById(1);
 
        assertNotNull(foundOrder);
        assertEquals(1, foundOrder.getOrderId());
        assertEquals("Pending", foundOrder.getOrderStatus());
 
        verify(orderRepository, times(1)).findById(1);
    }
 
    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(1)).thenReturn(java.util.Optional.empty());
 
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            orderService.getOrderById(1);
        });
 
        assertEquals("Order not found with id: 1", thrown.getMessage());
        verify(orderRepository, times(1)).findById(1);
    }
 
    @Test
    void testUpdateOrderStatus() {
        when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
 
        Order updatedOrder = orderService.updateOrderStatus(1, "Shipped");
 
        assertNotNull(updatedOrder);
        assertEquals("Shipped", updatedOrder.getOrderStatus());
 
        verify(orderRepository, times(1)).findById(1);
        verify(orderRepository, times(1)).save(any(Order.class));
    }
 
    @Test
    void testUpdateOrderStatus_NotFound() {
        when(orderRepository.findById(1)).thenReturn(java.util.Optional.empty());
 
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            orderService.updateOrderStatus(1, "Shipped");
        });
 
        assertEquals("Order not found with id: 1", thrown.getMessage());
        verify(orderRepository, times(1)).findById(1);
    }
 
    @Test
    void testCancelOrder() {
        when(orderRepository.findById(1)).thenReturn(java.util.Optional.of(order));
        doNothing().when(orderRepository).delete(order);
 
        orderService.cancelOrder(1);
 
        verify(orderRepository, times(1)).delete(order);
    }
}
