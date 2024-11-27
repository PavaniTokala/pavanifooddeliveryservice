package com.capgem.pavani.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="delivery_drivers")
public class DeliveryDriver {
    @Id
    @Column(name="driver_id")
    private int driverId;
    
    @Column(name="driver_name")
    private String driverName;
    @Column(name="driver_phone")
    private String driverPhone;
    @Column(name="driver_vehicle")
    private String driverVehicle;
    
    @Column(name="driver_location")  // New field for location
    private String driverLocation;

    @OneToMany(mappedBy = "deliveryDriver")
    @JsonIgnore
    private List<Order> orders;

	public DeliveryDriver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryDriver(int driverId, String driverName, String driverPhone, String driverVehicle,
			List<Order> orders) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverPhone = driverPhone;
		this.driverVehicle = driverVehicle;
		this.orders = orders;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getDriverVehicle() {
		return driverVehicle;
	}

	public void setDriverVehicle(String driverVehicle) {
		this.driverVehicle = driverVehicle;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

	@Override
	public String toString() {
		return "DeliveryDriver [driverId=" + driverId + ", driverName=" + driverName + ", driverPhone=" + driverPhone
				+ ", driverVehicle=" + driverVehicle + ", orders=" + orders + "]";
	}

    // Getters, setters, constructors, and toString
}

