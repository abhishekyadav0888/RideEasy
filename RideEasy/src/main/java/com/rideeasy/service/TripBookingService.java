package com.rideeasy.service;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.TripBooking;

import java.util.List;

public interface TripBookingService {
    public TripBooking insertTripBooking(TripBooking tripBooking,Integer customerId) throws RideEasyException;

    public TripBooking updateTripBooking(TripBooking tripBooking) throws RideEasyException;
    public TripBooking deleteTripBooking(Integer tripBookingId) throws RideEasyException;
    public List<TripBooking> viewAllTripsCustomer(Integer customerld) throws RideEasyException;

    public float calculateBill(Integer customerId) throws RideEasyException;

    public List<TripBooking> getAllTripBooking(Integer customerid) throws RideEasyException;



}
