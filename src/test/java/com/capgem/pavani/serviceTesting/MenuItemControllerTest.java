package com.capgem.pavani.serviceTesting;



import com.capgem.pavani.controllers.MenuItemController;
import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.services.MenuItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MenuItemControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MenuItemController menuItemController;

    @Mock
    private MenuItemService menuItemService;

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemController).build();

        menuItem = new MenuItem(1, "Pizza", "Delicious cheese pizza", 10.99, null);
    }

    @Test
    void testGetMenuItemsByRestaurant() throws Exception {
        List<MenuItem> menuItems = Arrays.asList(menuItem);
        when(menuItemService.getMenuItemsByRestaurant(1)).thenReturn(menuItems);

        mockMvc.perform(get("/api/restaurants/{restaurantId}/menu", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].itemId").value(menuItem.getItemId()))
                .andExpect(jsonPath("$[0].itemName").value(menuItem.getItemName()));

        verify(menuItemService, times(1)).getMenuItemsByRestaurant(1);
    }

    @Test
    void testAddMenuItemToRestaurant() throws Exception {
        when(menuItemService.addMenuItemToRestaurant(eq(1), any(MenuItem.class))).thenReturn(menuItem);

        String menuItemJson = """
                {
                    "itemId": 1,
                    "itemName": "Pizza",
                    "itemDescription": "Delicious cheese pizza",
                    "itemPrice": 10.99
                }
                """;

        mockMvc.perform(post("/api/restaurants/{restaurantId}/menu", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(menuItemJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemId").value(menuItem.getItemId()))
                .andExpect(jsonPath("$.itemName").value(menuItem.getItemName()));

        verify(menuItemService, times(1)).addMenuItemToRestaurant(eq(1), any(MenuItem.class));
    }

    @Test
    void testUpdateMenuItem() throws Exception {
        MenuItem updatedMenuItem = new MenuItem(1, "Updated Pizza", "Updated description", 12.99, null);
        when(menuItemService.updateMenuItem(eq(1), eq(1), any(MenuItem.class))).thenReturn(updatedMenuItem);

        String updatedMenuItemJson = """
                {
                    "itemId": 1,
                    "itemName": "Updated Pizza",
                    "itemDescription": "Updated description",
                    "itemPrice": 12.99
                }
                """;

        mockMvc.perform(put("/api/restaurants/{restaurantId}/menu/{itemId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedMenuItemJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value(updatedMenuItem.getItemName()))
                .andExpect(jsonPath("$.itemDescription").value(updatedMenuItem.getItemDescription()));

        verify(menuItemService, times(1)).updateMenuItem(eq(1), eq(1), any(MenuItem.class));
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        doNothing().when(menuItemService).deleteMenuItem(1, 1);

        mockMvc.perform(delete("/api/restaurants/{restaurantId}/menu/{itemId}", 1, 1))
                .andExpect(status().isNoContent());

        verify(menuItemService, times(1)).deleteMenuItem(1, 1);
    }
}
