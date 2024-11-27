package com.capgem.pavani.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.Rating;


public interface RatingRepository extends JpaRepository<Rating,Integer>{

//	List<Rating> findByCustomer_CustomerId(int customerId);

	List<Rating> findByRestaurant_RestaurantId(int restaurantId);
}
