package com.capgem.pavani.serviceTesting;


import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.entities.Restaurant;
import com.capgem.pavani.repositories.MenuItemRepository;
import com.capgem.pavani.repositories.RestaurantRepository;
import com.capgem.pavani.services.MenuItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant;
    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant(1, "Pizza Place", "123 Pizza St.", "123-456-7890", null, null, null);
        menuItem = new MenuItem(1, "Pizza", "Delicious cheese pizza", 10.99, restaurant);
    }

    @Test
    void testGetMenuItemsByRestaurant() {
        when(menuItemRepository.findByRestaurant_RestaurantId(1)).thenReturn(Arrays.asList(menuItem));

        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurant(1);

        assertNotNull(menuItems);
        assertEquals(1, menuItems.size());
        assertEquals("Pizza", menuItems.get(0).getItemName());
        verify(menuItemRepository, times(1)).findByRestaurant_RestaurantId(1);
    }

    @Test
    void testAddMenuItemToRestaurant_Success() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem result = menuItemService.addMenuItemToRestaurant(1, menuItem);

        assertNotNull(result);
        assertEquals("Pizza", result.getItemName());
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void testAddMenuItemToRestaurant_NotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            menuItemService.addMenuItemToRestaurant(1, menuItem);
        });

        assertEquals("Restaurant not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateMenuItem_Success() {
        MenuItem updatedMenuItem = new MenuItem(1, "Updated Pizza", "Updated description", 12.99, restaurant);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(menuItem));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(updatedMenuItem);

        MenuItem result = menuItemService.updateMenuItem(1, 1, updatedMenuItem);

        assertNotNull(result);
        assertEquals("Updated Pizza", result.getItemName());
        assertEquals("Updated description", result.getItemDescription());
        verify(menuItemRepository, times(1)).save(menuItem);
    }

    @Test
    void testUpdateMenuItem_NotFound() {
        MenuItem updatedMenuItem = new MenuItem(1, "Updated Pizza", "Updated description", 12.99, restaurant);
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            menuItemService.updateMenuItem(1, 1, updatedMenuItem);
        });

        assertEquals("Menu item or restaurant not found or mismatch", exception.getMessage());
    }

    @Test
    void testDeleteMenuItem_Success() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(menuItem));

        menuItemService.deleteMenuItem(1, 1);

        verify(menuItemRepository, times(1)).delete(menuItem);
    }

    @Test
    void testDeleteMenuItem_NotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
        when(menuItemRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            menuItemService.deleteMenuItem(1, 1);
        });

        assertEquals("Menu item or restaurant not found or mismatch", exception.getMessage());
    }
}
