package com.rideeasy.controller;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.TripBooking;
import com.rideeasy.service.TripBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/tripbookings")
public class TripBookingController {

    @Autowired
    private TripBookingService tripBookingService;


    @PostMapping
    public ResponseEntity<TripBooking> insertTripBooking(@Valid @RequestBody TripBooking tripBooking) {
        try {
            TripBooking insertedBooking = tripBookingService.insertTripBooking(tripBooking);
            return ResponseEntity.ok(insertedBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripBooking> updateTripBooking(@PathVariable("id") int tripBookingId,
                                                         @RequestBody TripBooking tripBooking) {
        try {
            TripBooking existingBooking = tripBookingService.updateTripBooking(tripBooking);
            return ResponseEntity.ok(existingBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TripBooking> deleteTripBooking(@PathVariable("id") int tripBookingId) {
        try {
            TripBooking deletedBooking = tripBookingService.deleteTripBooking(tripBookingId);
            return ResponseEntity.ok(deletedBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TripBooking>> viewAllTripsCustomer(@PathVariable("customerId") int customerId) {
        try {
            List<TripBooking> tripBookings = tripBookingService.viewAllTripsCustomer(customerId);
            return ResponseEntity.ok(tripBookings);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/customer/{customerId}/calculate-bill")
    public ResponseEntity<Double> calculateBill(@PathVariable("customerId") int customerId) {
        try {
            double billAmount = tripBookingService.calculateBill(customerId);
            return ResponseEntity.ok(billAmount);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
