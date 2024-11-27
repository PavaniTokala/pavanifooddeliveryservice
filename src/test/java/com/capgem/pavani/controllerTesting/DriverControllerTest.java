package com.capgem.pavani.controllerTesting;
import com.capgem.pavani.controllers.DriverController;
import com.capgem.pavani.entities.DeliveryDriver;
import com.capgem.pavani.entities.Order;
import com.capgem.pavani.services.DriverService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
 
@ExtendWith(MockitoExtension.class)
public class DriverControllerTest {
 
    @InjectMocks
    private DriverController driverController;
 
    @Mock
    private DriverService driverService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
    }
 
    @Test
    public void testGetAllDrivers() throws Exception {
        DeliveryDriver driver = new DeliveryDriver(1, "John Doe", "123456789", "Car", null);
        List<DeliveryDriver> drivers = Arrays.asList(driver);
        when(driverService.getAllDrivers()).thenReturn(drivers);
 
        mockMvc.perform(get("/api/drivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].driverName").value("John Doe"));
 
        verify(driverService, times(1)).getAllDrivers();
    }
 
    @Test
    public void testGetDriverById() throws Exception {
        DeliveryDriver driver = new DeliveryDriver(1, "John Doe", "123456789", "Car", null);
        when(driverService.getDriverById(1)).thenReturn(driver);
 
        mockMvc.perform(get("/api/drivers/{driverId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.driverName").value("John Doe"));
 
        verify(driverService, times(1)).getDriverById(1);
    }
 
//    @Test
//    public void testAssignDriverToOrder() throws Exception {
//        Order order = new Order();  // Assume a valid order object is created
//        when(driverService.assignDriverToOrder(1, 1)).thenReturn(order);
// 
//        mockMvc.perform(put("/api/drivers/orders/{orderId}/assignDriver/{driverId}", 1, 1)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
// 
//        verify(driverService, times(1)).assignDriverToOrder(1, 1);
//    }
 
    @Test
    public void testGetOrdersAssignedToDriver() throws Exception {
        Order order = new Order();  // Assume a valid order object is created
        List<Order> orders = Arrays.asList(order);
        when(driverService.getOrdersAssignedToDriver(1)).thenReturn(orders);
 
        mockMvc.perform(get("/api/drivers/{driverId}/orders", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
 
        verify(driverService, times(1)).getOrdersAssignedToDriver(1);
    }
}
 