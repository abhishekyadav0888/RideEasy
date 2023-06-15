package com.rideeasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Admin;
import com.rideeasy.model.TripBooking;
import com.rideeasy.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<Admin> insertAdminController(@Valid @RequestBody Admin admin){
		try {
			Admin insertedAdmin = adminService.insertAdmin(admin);
			return ResponseEntity.status(HttpStatus.CREATED).body(insertedAdmin);
		} catch (RideEasyException ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/{adminId}")
	public ResponseEntity<Admin> updateAdminController(@PathVariable Integer adminId, @Valid @RequestBody Admin admin){
		try {
			Admin updatedAdmin = adminService.updateAdmin(adminId, admin);
			return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
		} catch (RideEasyException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteAdminController(@PathVariable Integer adminId){
		try {
			Admin deletedAdmin = adminService.deleteAdmin(adminId);
			return ResponseEntity.status(HttpStatus.OK).body(deletedAdmin);
		} catch (RideEasyException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<TripBooking>> getAllTripsOfCustomerController(@PathVariable Integer customerId){
		try {
			List<TripBooking> tripBookings = adminService.getAllTripsOfCustomer(customerId);
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
