package com.rental.service;

import java.sql.*;

import com.rental.database.DBConnection;
import com.rental.model.RentalModel;

public class RentalService {

	// Insert method which insert data to Appointment table
	public String insertRental(String customer, String vehicle, String duration, Date date) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into rental(`id`,`customer`,`vehicle`,`duration`,`date`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customer);
			preparedStmt.setString(3, vehicle);
			preparedStmt.setString(4, duration);
			preparedStmt.setDate(5, date);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// GetAll inserted Appointments data
	public String readRental() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// displaying HTML table
			output = "<table border=\"1\"><tr><th>id</th><th>customer</th><th>vehicle</th><th>duration</th><th>date</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from rental";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String customer = rs.getString("customer");
				String vehicle = rs.getString("vehicle");
				String duration = rs.getString("duration");
				String date = rs.getString("date");

				// Add into the HTML table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + customer + "</td>";
				output += "<td>" + vehicle + "</td>";
				output += "<td>" + duration + "</td>";
				output += "<td>" + date + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Delete\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Update Appointment table
	public String updateRental(String id, String customer, String vehicle, String duration, String date) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE rental SET customer=?,vehicle=?,duration=?,date=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, customer);
			preparedStmt.setString(2, vehicle);
			preparedStmt.setString(3, duration);
			preparedStmt.setString(4, date);
			preparedStmt.setInt(5, Integer.parseInt(id));

			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Delete Appointment table
	public String deleteRental(String id) {
		String output = "";
		java.sql.Date sqlDate = null;

		//  Get Appointment data by ID
		try {
			Connection con1 = DBConnection.connect();

			String query1 = "delete from rental where id=?";
			PreparedStatement preparedStmt1 = con1.prepareStatement(query1);
			preparedStmt1.setInt(1, Integer.parseInt(id));

			preparedStmt1.executeQuery();

			con1.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	// Get Appointment data by ID
	public RentalModel getRentalById(String aId) {

		RentalModel rental = new RentalModel();

		try {
			Connection con = DBConnection.connect();

			String query = "select * from rental where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(aId));

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				rental.setId(Integer.parseInt(aId));
				rental.setCustomer(rs.getString("customer").toString());
				rental.setVehicle(rs.getString("vehicle"));
				rental.setDuration(rs.getString("duration"));
				java.util.Date utilDate = rs.getDate("date");
				rental.setDate(utilDate);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return rental;
	}

	
}
