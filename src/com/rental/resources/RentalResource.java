package com.rental.resources;

import java.sql.Date;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rental.model.RentalModel;
import com.rental.service.RentalService;
//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/rentalSRV")
public class RentalResource {

	RentalService rentalSRV = new RentalService();

	@GET
	@Path("/Test")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello world.";
	}

	// GetAll Appointments
	@GET
	@Path("/getAllRentals")
	@Produces(MediaType.TEXT_HTML)
	public String readRental() {
		return rentalSRV.readRental();
	}

	// Add Appointment
	@POST
	@Path("/addRental")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("customer") String customer, @FormParam("vehicle") String vehicle,
			@FormParam("duration") String duration, @FormParam("date") Date date) {
		String output = rentalSRV.insertRental(customer, vehicle, duration, date);
		return output;
	}

	// Update Appointment
	@PUT
	@Path("/updateRental")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String appointmentData) {
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		String id = appointmentObject.get("id").getAsString();
		String userId = appointmentObject.get("customer").getAsString();
		String hosId = appointmentObject.get("vehicle").getAsString();
		String docId = appointmentObject.get("duration").getAsString();
		String date = appointmentObject.get("date").getAsString();
		String output = rentalSRV.updateRental(id, userId, hosId, docId, date);
		return output;
	}

	// delete Appointment
	@DELETE
	@Path("/removeRental/{id}")
	public String deleteAppointment(@PathParam("id") String id) {
		String output = rentalSRV.deleteRental(id);
		return output;
	}

	// Get Appointment By Id
	@GET
	@Path("/getRentalsById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RentalModel getAppointmentById(@PathParam("id")String id) {
		RentalModel appointment = rentalSRV.getRentalById(id);
		return appointment;
	}
	

}
