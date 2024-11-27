package com.capgem.pavani.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Restaurants")
public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @Column(name = "restaurant_phone")
    private String restaurantPhone;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Rating> ratings;

	public Restaurant() {
		super();
	}

	public Restaurant(int restaurantId, String restaurantName, String restaurantAddress, String restaurantPhone,
			List<MenuItem> menuItems, List<Order> orders, List<Rating> ratings) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
		this.menuItems = menuItems;
		this.orders = orders;
		this.ratings = ratings;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantPhone() {
		return restaurantPhone;
	}

	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", restaurantPhone=" + restaurantPhone + "]";
	}
    
}

