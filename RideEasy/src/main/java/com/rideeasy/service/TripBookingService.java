package com.rideeasy.service;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.TripBooking;

import java.util.List;

public interface TripBookingService {
    public TripBooking insertTripBooking(TripBooking tripBooking) throws RideEasyException;

    public TripBooking updateTripBooking(TripBooking tripBooking) throws RideEasyException;
    public TripBooking deleteTripBooking(int tripBookingId) throws RideEasyException;
    public List<TripBooking> viewAllTripsCustomer(int customerld) throws RideEasyException;

    public double calculateBill(int customerId) throws RideEasyException;



}
