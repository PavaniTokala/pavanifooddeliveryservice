package com.capgem.pavani.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.services.MenuItemService;
import com.capgem.pavani.services.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class MenuItemController {
	
	@Autowired
    private MenuItemService menuItemService;
	// Retrieve all menu items of a specific restaurant
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurant(@PathVariable int restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByRestaurant(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    // Add a new menu item to a specific restaurant
    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuItem> addMenuItemToRestaurant(@PathVariable int restaurantId, @RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.addMenuItemToRestaurant(restaurantId, menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
    }

    // Update details of a specific menu item
    @PutMapping("/{restaurantId}/menu/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable int restaurantId, @PathVariable int itemId, @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(restaurantId, itemId, menuItemDetails);
        return ResponseEntity.ok(updatedMenuItem);
    }

    // Delete a specific menu item from a restaurant
    @DeleteMapping("/{restaurantId}/menu/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int restaurantId, @PathVariable int itemId) {
        menuItemService.deleteMenuItem(restaurantId, itemId);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }

}
