package com.capgem.pavani.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.Order;


public interface OrderRepository extends JpaRepository<Order,Integer>{

	List<Order> findByDeliveryDriver_DriverId(int driverId);

	List<Order> findByCustomer_CustomerId(int customerId);

}
