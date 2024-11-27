package com.capgem.pavani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgem.pavani.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
