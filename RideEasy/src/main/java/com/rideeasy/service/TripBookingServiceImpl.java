package com.rideeasy.service;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Customer;
import com.rideeasy.model.Driver;
import com.rideeasy.model.TripBooking;
import com.rideeasy.repository.CustomerRepository;
import com.rideeasy.repository.TripBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripBookingServiceImpl implements TripBookingService {

    private static final Logger logger = LoggerFactory.getLogger(TripBookingServiceImpl.class);
    @Autowired
    private TripBookingRepository tripBookingRepository;

    @Autowired
    private DriverRepository DriverRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public TripBooking insertTripBooking(TripBooking tripBooking) throws RideEasyException {
        logger.info("Inserting trip booking: {}", tripBooking);
        try{
            Optional<Customer> customer = customerRepository.findById(tripBooking.getCustomer().getCustomerId());

            if (customer.isPresent()) {
                Customer cust = customer.get();
                TripBooking tripB = new TripBooking();


                tripB.setFromLocation(tripBooking.getFromLocation());
                tripB.setToLocation(tripBooking.getToLocation());
                tripB.setFromDateTime(tripBooking.getFromDateTime());
                tripB.setToDateTime(tripBooking.getToDateTime());
                int min = 10;
                int max = 100;
                float distance = (float) Math.floor(Math.random() * (max - min + 1) + min);
                tripB.setDistanceInKm(distance);

                tripB.setCustomer(cust);


//            List<Driver> driverlist = DriverRepository.findAll();
//            Driver driver = null;
//            for(int i = 0;i<driverlist.size();i++) {
//                if(driverlist.get(i).getAvailablity() == true) {
//                    driver = driverlist.get(i);
//                    break;
//                }
//            }
//
//            if( driver == null ) throw new RideEasyException("No Driver Available at the moment");
//
//            tripB.setDriver(driver);
//
//            driver.getTripBookingList().add(tripB);
//            driver.setAvailablity(false);


                cust.getTripBookings().add(tripB);

                tripBookingRepository.save(tripB);


                return tripB;

            } else {
                throw new RideEasyException("Customer not found with id " + tripBooking.getCustomer().getCustomerId());
            }
        }
        catch (Exception e) {
            logger.error("Error occurred while inserting trip booking", e);
            throw new RideEasyException("Failed to insert trip booking", e);
        }
    }

    @Override
    public TripBooking updateTripBooking(TripBooking tripBooking) throws RideEasyException {
        logger.info("Updating trip booking: {}", tripBooking);

        try{
            Optional<TripBooking> opt = tripBookingRepository.findById(tripBooking.getTripBookingId());

            if (opt.isPresent()) {
                TripBooking updatedTrip = tripBookingRepository.save(tripBooking);
                return updatedTrip;
            } else {
                throw new RideEasyException("User not found!");
            }
        }
        catch (Exception e) {
            logger.error("Error occurred while updating trip booking", e);
            throw new RideEasyException("Failed to update trip booking", e);
        }
    }

    @Override
    public TripBooking deleteTripBooking(int tripBookingId) throws RideEasyException {
        logger.info("Deleting trip booking with ID: {}", tripBookingId);

        try{
            TripBooking tripBooking = tripBookingRepository.findById(tripBookingId)
                    .orElseThrow(() -> new RideEasyException("Trip booking not found with ID: " + tripBookingId));

            tripBookingRepository.delete(tripBooking);
            return tripBooking;
        }
        catch (Exception e) {
            logger.error("Error occurred while deleting trip booking", e);
            throw new RideEasyException("Failed to delete trip booking", e);
        }
    }

    @Override
    public List<TripBooking> viewAllTripsCustomer(int customerld) throws RideEasyException {
        logger.info("Retrieving all trip bookings for customer with ID: {}", customerId);
        try{
            Customer customer = customerRepository.findById(customerld)
                    .orElseThrow(() -> new RideEasyException("Customer not found with ID: " + customerld));

            return tripBookingRepository.findAllByCustomerId(customerId);
        }
        catch (Exception e) {
            logger.error("Error occurred while retrieving trip bookings for customer with ID: {}", customerld, e);
            throw new RideEasyException("Failed to retrieve trip bookings for customer", e);
        }
    }

    @Override
    public double calculateBill(int customerId) throws RideEasyException {
        logger.info("Calculating bill for customer with ID: {}", customerId);

        try{
            List<TripBooking> tripBookings = tripBookingRepository.findAllByCustomerId(customerId);

            // Sort the tripBookings by fromDateTime in descending order to get the last trip
            tripBookings.sort(Comparator.comparing(TripBooking::getFromDateTime).reversed());

            if (!tripBookings.isEmpty()) {
                TripBooking lastTripBooking = tripBookings.get(0); // Get the first (last) trip booking

                if (lastTripBooking.getStatus() != null && lastTripBooking.getStatus()) {
                    Float distance = lastTripBooking.getDistanceInKm();
                    Float perKmRate = lastTripBooking.getDriver().getCab().getPerKmRate();
                    double tripBill = distance * perKmRate;
                    logger.info("Bill calculated successfully for the last trip of customer with ID {}: {}", customerId, tripBill);

                    return tripBill;
                }
            }
            logger.warn("No completed trip found for customer with ID: {}", customerId);
            return 0.0;
        }
        catch (Exception e) {
            logger.error("Error occurred while calculating bill for customer with ID: {}", customerId, e);
            throw new RideEasyException("Failed to calculate bill for customer", e);
        }
    }

}
