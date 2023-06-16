package com.rideeasy.controller;

import java.time.LocalDateTime;
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

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Admin;
import com.rideeasy.model.TripBooking;
import com.rideeasy.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public ResponseEntity<Admin> insertAdminController(@Valid @RequestBody Admin admin){
		try {
			log.info("Try to insert new Admin : AdminController");
			Admin insertedAdmin = adminService.insertAdmin(admin);
			log.info("Admin inserted successfully : AdminController");
			return ResponseEntity.status(HttpStatus.CREATED).body(insertedAdmin);
		} catch (RideEasyException ex){
			log.warn("Admin insertion failed : AdminController");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("/{adminId}")
	public ResponseEntity<Admin> updateAdminController(@PathVariable Integer adminId, @Valid @RequestBody Admin admin){
		try {
			log.info("Try to update Admin : AdminController");
			Admin updatedAdmin = adminService.updateAdmin(adminId, admin);
			log.info("Admin updated successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
		} catch (RideEasyException ex) {
			log.warn("Admin updation failed : AdminController");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteAdminController(@PathVariable Integer adminId){
		try {
			log.info("Try to delete Admin : AdminController");
			Admin deletedAdmin = adminService.deleteAdmin(adminId);
			log.info("Admin deleted successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(deletedAdmin);
		} catch (RideEasyException ex) {
			log.warn("Admin deletation failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<TripBooking>> getAllTripsOfCustomerController(@PathVariable Integer customerId){
		try {
			log.info("Try to get all trips of a customer : AdminController");
			List<TripBooking> tripBookings = adminService.getAllTripsOfCustomer(customerId);
			log.info("All trips got successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			log.warn("Getting trips of a customer failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/cab/{cabId}")
	public ResponseEntity<List<TripBooking>> getTripsCabWiseController(@PathVariable Integer cabId){
		try {
			log.info("Try to get all trips cab wise : AdminController");
			List<TripBooking> tripBookings = adminService.getTripsCabWise(cabId);
			log.info("All trips got successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			log.warn("Getting trips cab wise failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<TripBooking>> getTripsCustomerWiseController(@PathVariable Integer customerId){
		try {
			log.info("Try to get all trips customer wise : AdminController");
			List<TripBooking> tripBookings = adminService.getTripsCustomerWise(customerId);
			log.info("All trips got successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			log.warn("Getting trips customer wise failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/date/{dateTime}")
	public ResponseEntity<List<TripBooking>> getTripsDateWiseController(@PathVariable LocalDateTime dateTime){
		try {
			log.info("Try to get all trips date wise : AdminController");
			List<TripBooking> tripBookings = adminService.getTripsDateWise(dateTime);
			log.info("All trips got successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			log.warn("Getting trips date wise failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/forDays/{customerId}/{fromDate}/{toDate}")
	public ResponseEntity<List<TripBooking>> getAllTripsForDaysController(@PathVariable Integer customerId, LocalDateTime fromDate, LocalDateTime toDate){
		try {
			log.info("Try to get all trips for days : AdminController");
			List<TripBooking> tripBookings = adminService.getAllTripsForDays(customerId, fromDate, toDate);
			log.info("All trips got successfully : AdminController");
			return ResponseEntity.status(HttpStatus.OK).body(tripBookings);
		} catch (RideEasyException ex) {
			log.warn("Getting trips for days failed : AdminController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
