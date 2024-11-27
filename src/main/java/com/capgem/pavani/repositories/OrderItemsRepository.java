package com.capgem.pavani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer>{

}
