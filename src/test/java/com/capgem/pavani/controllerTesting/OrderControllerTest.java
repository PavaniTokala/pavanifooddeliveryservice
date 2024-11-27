package com.capgem.pavani.controllerTesting;



import com.capgem.pavani.controllers.OrderController;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.OrderService;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import java.sql.Date;
 
public class OrderControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock

    private OrderService orderService;
 
    @InjectMocks

    private OrderController orderController;
 
    private Order order;
 
    @BeforeEach

    public void setUp() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
 
        // Initialize a sample order object

        order = new Order(1, new Date(0), "Pending", null, null, null, null, null);

    }
 
    // Test placing a new order

    @Test

    public void testPlaceOrder() throws Exception {

        // Mock the orderService placeOrder method

        when(orderService.placeOrder(any(Order.class))).thenReturn(order);
 
        mockMvc.perform(post("/api/orders")

                .contentType("application/json")

                .content("{\"orderId\":1, \"orderDate\":\"2024-11-27\", \"orderStatus\":\"Pending\"}"))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.orderId").value(1))

                .andExpect(jsonPath("$.orderStatus").value("Pending"));
 
        verify(orderService, times(1)).placeOrder(any(Order.class));

    }
 
    // Test getting an order by ID

    @Test

    public void testGetOrderById() throws Exception {

        // Mock the orderService getOrderById method

        when(orderService.getOrderById(1)).thenReturn(order);
 
        mockMvc.perform(get("/api/orders/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.orderId").value(1))

                .andExpect(jsonPath("$.orderStatus").value("Pending"));
 
        verify(orderService, times(1)).getOrderById(1);

    }
 
    // Test updating the status of an order

    @Test

    public void testUpdateOrderStatus() throws Exception {

        // Mock the orderService updateOrderStatus method

        when(orderService.updateOrderStatus(1, "Shipped")).thenReturn(order);
 
        mockMvc.perform(put("/api/orders/1/status?status=Shipped"))

                .andExpect(status().isOk());

             //   .andExpect(jsonPath("$.orderStatus").value("Shipped"));
 
        verify(orderService, times(1)).updateOrderStatus(1, "Shipped");

    }
 
    // Test canceling an order

    //@Test

//    public void testCancelOrder() throws Exception {
//
//        // Mock the orderService cancelOrder method
//
//        doNothing().when(orderService).cancelOrder(1);
// 
//        mockMvc.perform(delete("/api/orders/1"))
//
// 
//        verify(orderService, times(1)).cancelOrder(1);
//
//    }

}

 
