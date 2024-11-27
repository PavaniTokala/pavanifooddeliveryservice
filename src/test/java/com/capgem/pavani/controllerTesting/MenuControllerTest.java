package com.capgem.pavani.controllerTesting;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgem.pavani.controllers.MenuItemController;
import com.capgem.pavani.entities.MenuItem;
import com.capgem.pavani.services.MenuItemService;

@ExtendWith(MockitoExtension.class)
public class MenuControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuController;

    private MockMvc mockMvc;

    @Autowired
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    public void testGetMenuItemsByRestaurant() throws Exception {
        MenuItem menuItem = new MenuItem(1, "Pizza", "Delicious cheese pizza", 10.99, null); // Sample menu item
        List<MenuItem> menuItems = Arrays.asList(menuItem);
        when(menuItemService.getMenuItemsByRestaurant(1)).thenReturn(menuItems);

        mockMvc.perform(get("/api/restaurants/{restaurantId}/menu", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].itemName").value("Pizza"))
                .andExpect(jsonPath("$[0].itemPrice").value(10.99));

        verify(menuItemService, times(1)).getMenuItemsByRestaurant(1);
    }

    @Test
    public void testDeleteMenuItem() throws Exception {
        doNothing().when(menuItemService).deleteMenuItem(1, 1);

        mockMvc.perform(delete("/api/restaurants/{restaurantId}/menu/{itemId}", 1, 1))
                .andExpect(status().isNoContent());

        verify(menuItemService, times(1)).deleteMenuItem(1, 1);
    }
}
