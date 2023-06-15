package com.rideeasy.service;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Driver;
import com.rideeasy.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;


    /**
     * @param driver
     * @return driver
     * Take a driver object to be persisted and return persisted driver;
     */
    @Override
    public Driver insertDriver(Driver driver) {
        log.info("Class: DriverServiceImpl, method: insertDriver started ");

        // checking for null;
        if (driver==null) throw new RideEasyException("null value");

        // checking for already existing username;
         Optional<Driver> opt= driverRepository.findByUserName(driver.getUserName());
         if(opt.isPresent())
             throw new RideEasyException("This username already exists, please provide another username");

         // checking for already existing mobile number;
        Optional<Driver> opt1= driverRepository.findByMobileNumber(driver.getMobileNumber());
        if(opt1.isPresent())
            throw new RideEasyException("This mobile number already exists, please provide another mobile number");

        // checking for already existing email;
        Optional<Driver> opt2= driverRepository.findByEmail(driver.getEmail());
        if(opt2.isPresent())
            throw new RideEasyException("This email already exists, please provide another email");

        //persisting the driver object;
        Driver persistedDriver= driverRepository.save(driver);
        log.info("Class: DriverServiceImpl, method: insertDriver returned "+ persistedDriver);
        return persistedDriver;
    }

    /**
     * @param driver
     * @return updatedDriver
     * Take a driver object to be updated, return updated driver;
     */
    @Override
    public Driver updateDriver(Driver driver) {
        log.info("Class: DriverServiceImpl, method: updateDriver started ");

        // checking for null;
        if (driver==null) throw new RideEasyException("null value");

        Optional<Driver> opt= driverRepository.findByUserName(driver.getUserName());
        if(opt.isEmpty())
            throw new RideEasyException("This username does not exists");

        //updating the driver object;
        Driver updatedDriver= driverRepository.save(driver);
        log.info("Class: DriverServiceImpl, method: updateDriver returned "+ updatedDriver);
        return updatedDriver;
    }

    /**
     * @param driverId
     * @return deletedDriver
     * Take driver id of Integet type, return deletedDriver;
     */
    @Override
    public Driver deleteDriver(Integer driverId) {
        log.info("Class: DriverServiceImpl, method: deleteDriver started ");

        Optional<Driver> opt= driverRepository.findById(driverId);
        if(opt.isEmpty())
            throw new RideEasyException("Driver with id: "+driverId+ "does not exist.");

        driverRepository.deleteById(driverId);
        log.info("Class: DriverServiceImpl, method: deleteDriver returned deleted driver");
        return opt.get();
    }

    /**
     * @return List<Driver>
     *     return list of drivers having rating >=4.5 ;
     */
    @Override
    public List<Driver> viewBestDrivers() {
        log.info("Class: DriverServiceImpl, method: viewBestDrivers started ");

        List<Driver> drivers= driverRepository.findByRatingGreaterThanOrEqual(4.5F);
        if(drivers.isEmpty())
            throw new RideEasyException("No driver found");
        log.info("Class: DriverServiceImpl, method: viewBestDrivers ended ");
        return drivers;
    }

    /**
     * @param driverId
     * @return driver
     * Take driver id of integer type, returns driver;
     */
    @Override
    public Driver viewDriver(Integer driverId) {
        log.info("Class: DriverServiceImpl, method: viewDriver started ");
        Driver driver= driverRepository.findById(driverId).orElseThrow(()-> new RideEasyException("driver with id: "+driverId+" not found"));
        log.info("Class: DriverServiceImpl, method: viewDriver returned "+driver);
        return driver;
    }

}
