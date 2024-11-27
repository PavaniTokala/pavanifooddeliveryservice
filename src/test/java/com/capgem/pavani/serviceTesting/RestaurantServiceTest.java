package com.capgem.pavani.serviceTesting;

import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.entities.Restaurant;
import com.capgem.pavani.exception.ResourceNotFoundException;
import com.capgem.pavani.repositories.MenuItemRepository;
import com.capgem.pavani.repositories.RestaurantRepository;
import com.capgem.pavani.services.RestaurantService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Arrays;
import java.util.Optional;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
 
    @InjectMocks
    private RestaurantService restaurantService;
 
    @Mock
    private RestaurantRepository restaurantRepository;
 
    @Mock
    private MenuItemRepository menuItemRepository;
 
    private Restaurant restaurant;
    private MenuItem menuItem;
 
    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant(1, "Pizza Place", "123 Pizza St.", "123-456-7890", null, null, null);
        menuItem = new MenuItem(1, "Pizza", "Delicious cheese pizza", 10.99, restaurant);
    }
 
    @Test
    public void testGetAllRestaurants() {
        Restaurant restaurant2 = new Restaurant(2, "Burger Joint", "456 Burger Rd.", "987-654-3210", null, null, null);
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant, restaurant2));
 
        assertEquals(2, restaurantService.getAllRestaurants().size());
        verify(restaurantRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetRestaurantById_Success() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
 
        Restaurant result = restaurantService.getRestaurantById(1);
 
        assertNotNull(result);
        assertEquals(1, result.getRestaurantId());
        assertEquals("Pizza Place", result.getRestaurantName());
        verify(restaurantRepository, times(1)).findById(1);
    }
 
    @Test
    public void testGetRestaurantById_NotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
 
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            restaurantService.getRestaurantById(1);
        });
 
        assertEquals("Restaurant not found with id: 1", exception.getMessage());
    }
 
    @Test
    public void testCreateRestaurant() {
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
 
        Restaurant result = restaurantService.createRestaurant(restaurant);
 
        assertNotNull(result);
        assertEquals("Pizza Place", result.getRestaurantName());
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }
 
    @Test
    public void testUpdateRestaurant_Success() {
        Restaurant updatedRestaurant = new Restaurant(1, "Pizza Palace", "123 Pizza St.", "123-456-7890", null, null, null);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(updatedRestaurant);
 
        Restaurant result = restaurantService.updateRestaurant(1, updatedRestaurant);
 
        assertNotNull(result);
        assertEquals("Pizza Palace", result.getRestaurantName());
        verify(restaurantRepository, times(1)).findById(1);
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }
 
    @Test
    public void testUpdateRestaurant_NotFound() {
        Restaurant updatedRestaurant = new Restaurant(1, "Pizza Palace", "123 Pizza St.", "123-456-7890", null, null, null);
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
 
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restaurantService.updateRestaurant(1, updatedRestaurant);
        });
 
        assertEquals("Restaurant not found with id: 1", exception.getMessage());
    }
 
    @Test
    public void testDeleteRestaurant_Success() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
 
        restaurantService.deleteRestaurant(1);
 
        verify(restaurantRepository, times(1)).findById(1);
        verify(restaurantRepository, times(1)).delete(any(Restaurant.class));
    }
 
    @Test
    public void testDeleteRestaurant_NotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
 
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restaurantService.deleteRestaurant(1);
        });
 
        assertEquals("Restaurant not found with id: 1", exception.getMessage());
    }
 

}
 
