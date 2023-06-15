package com.rideeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rideeasy.model.TripBooking;

public interface TripBookingRepositorya extends JpaRepository<TripBooking, Integer>, PagingAndSortingRepository<TripBooking, Integer> {
	
	public List<TripBooking> getAllTrips();

}
