//package com.capgem.harshit.entities;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="favorite_restaurant")
//public class FavoriteRestaurant {
//    @Id
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private Restaurant restaurant;
//
//	public FavoriteRestaurant() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public FavoriteRestaurant(int id, Customer customer, Restaurant restaurant) {
//		super();
//		this.id = id;
//		this.customer = customer;
//		this.restaurant = restaurant;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public Restaurant getRestaurant() {
//		return restaurant;
//	}
//
//	public void setRestaurant(Restaurant restaurant) {
//		this.restaurant = restaurant;
//	}
//
//	@Override
//	public String toString() {
//		return "FavoriteRestaurant [id=" + id + ", customer=" + customer + ", restaurant=" + restaurant + "]";
//	}
//    
//    
//
//    // Getters, setters, constructors, and toString
//}



