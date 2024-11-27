package com.capgem.pavani.controllerTesting;

import com.capgem.pavani.controllers.RestaurantController;
import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.entities.Restaurant;
import com.capgem.pavani.services.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Arrays;
import java.util.List;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {
 
    @InjectMocks
    private RestaurantController restaurantController;
 
    @Mock
    private RestaurantService restaurantService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }
 
    @Test
    public void testGetAllRestaurants() throws Exception {
        Restaurant restaurant = new Restaurant(1, "Pizza Place", "123 Pizza St.", "123-456-7890", null, null, null);
        List<Restaurant> restaurants = Arrays.asList(restaurant);
        when(restaurantService.getAllRestaurants()).thenReturn(restaurants);
 
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].restaurantName").value("Pizza Place"));
 
        verify(restaurantService, times(1)).getAllRestaurants();
    }
 
    @Test
    public void testGetRestaurantById() throws Exception {
        Restaurant restaurant = new Restaurant(1, "Pizza Place", "123 Pizza St.", "123-456-7890", null, null, null);
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);
 
        mockMvc.perform(get("/api/restaurants/{restaurantId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurantName").value("Pizza Place"));
 
        verify(restaurantService, times(1)).getRestaurantById(1);
    }
 
    @Test
    public void testCreateRestaurant() throws Exception {
        Restaurant newRestaurant = new Restaurant(2, "Burger Joint", "456 Burger Rd.", "987-654-3210", null, null, null);
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(newRestaurant);
 
        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newRestaurant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.restaurantName").value("Burger Joint"));
 
        verify(restaurantService, times(1)).createRestaurant(any(Restaurant.class));
    }
 
    
 
//    @Test
//    public void testDeleteRestaurant() throws Exception {
//        doNothing().when(restaurantService).deleteRestaurant(1);
// 
//        mockMvc.perform(delete("/api/restaurants/{restaurantId}", 1))
//                .andExpect(status().isNoContent());
// 
//        verify(restaurantService, times(1)).deleteRestaurant(1);
//    }
 
}

