package com.capgem.pavani.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {

	List<MenuItem> findByRestaurant_RestaurantId(int restaurantId);


}
