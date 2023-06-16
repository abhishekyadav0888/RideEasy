package com.rideeasy.repository;

import com.rideeasy.model.Customer;
import com.rideeasy.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {

    @Query("SELECT tb FROM TripBooking tb")
    public List<TripBooking> findAllTripBooking();
//    public List<TripBooking> findAllByCustomerId(int customerId);

    List<TripBooking> findAllByCustomer(Customer customer);
}
