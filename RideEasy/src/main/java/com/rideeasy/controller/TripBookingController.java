package com.rideeasy.controller;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.TripBooking;
import com.rideeasy.service.TripBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip-bookings")
public class TripBookingController {

    @Autowired
    private TripBookingService tripBookingService;


    @PostMapping("/{customerId}")
    public ResponseEntity<TripBooking> insertTripBooking(@Valid @RequestBody TripBooking tripBooking , @PathVariable("customerId") Integer customerId) {
        try {
            TripBooking createdTripBooking = tripBookingService.insertTripBooking(tripBooking,customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTripBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<TripBooking> updateTripBooking(
              @Valid @RequestBody TripBooking tripBooking) {
//        tripBooking.setTripBookingId(tripBooking);
        try {
            TripBooking updatedTripBooking = tripBookingService.updateTripBooking(tripBooking);
            return ResponseEntity.ok(updatedTripBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{tripBookingId}")
    public ResponseEntity<TripBooking> deleteTripBooking(@PathVariable("tripBookingId") int tripBookingId) {
        try {
            TripBooking deletedTripBooking = tripBookingService.deleteTripBooking(tripBookingId);
            return ResponseEntity.ok(deletedTripBooking);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TripBooking>> viewAllTripsCustomer(@PathVariable("customerId") int customerId) {
        try {
            List<TripBooking> tripBookings = tripBookingService.viewAllTripsCustomer(customerId);
            return ResponseEntity.ok(tripBookings);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/customer/{customerId}/calculate-bill")
    public ResponseEntity<Double> calculateBill(@PathVariable("customerId") Integer customerId) {
        try {
            double billAmount = tripBookingService.calculateBill(customerId);
            return ResponseEntity.ok(billAmount);
        } catch (RideEasyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
