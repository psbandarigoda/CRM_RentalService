package com.rental.model;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RentalModel {


	private Integer id;
	private String customer;
	private String vehicle;
	private String duration;
	private Date date;

	
	public RentalModel() {
	
	}

	
	public RentalModel(Integer id, String patientId, String hospital, String doctor, Date date) {
		super();
		this.id = id;
		this.customer = patientId;
		this.vehicle = hospital;
		this.duration = doctor;
		this.date = date;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", customer=" + customer + ", vehicle=" + vehicle + ", duration=" + duration + ", date="
				+ date + "]";
	}
	
	
}
