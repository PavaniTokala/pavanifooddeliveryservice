package com.capgem.pavani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
