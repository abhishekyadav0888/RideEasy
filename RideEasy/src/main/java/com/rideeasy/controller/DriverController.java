package com.rideeasy.controller;

import com.rideeasy.model.Driver;
import com.rideeasy.service.DriverService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public ResponseEntity<Driver> addDriverHandler( @Valid @RequestBody Driver driver){
        log.info("Class: DriverController, method: addDriverHandler started");
//        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        Driver persistedDriver= driverService.insertDriver(driver);
        log.info("Class: DriverController, method: addDriverHandler returned "+ persistedDriver);
        return new ResponseEntity<>(persistedDriver, HttpStatus.ACCEPTED);
    }
    @PutMapping ("/update")
    public ResponseEntity<Driver> updateDriverHandler( @Valid @RequestBody Driver driver){
        log.info("Class: DriverController, method: updateDriverHandler started");
        Driver updatedDriver= driverService.updateDriver(driver);
        log.info("Class: DriverController, method: updateDriverHandler returned "+updatedDriver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Driver> deleteDriverHandler(@PathVariable("id") Integer driverId ){
        log.info("Class: DriverController, method: deleteDriverHandler started");
        Driver deletedDriver= driverService.deleteDriver(driverId);
        log.info("Class: DriverController, method: deleteDriverHandler returned "+deletedDriver);
        return new ResponseEntity<>(deletedDriver, HttpStatus.OK);
    }
    @GetMapping ("/view/best")
    public ResponseEntity<List<Driver>> viewBestDriversHandler( ){
        log.info("Class: DriverController, method: viewBestDriversHandler started");
        List<Driver> drivers= driverService.viewBestDrivers();
        log.info("Class: DriverController, method: viewBestDriversHandler ended ");
        return new ResponseEntity<>(drivers, HttpStatus.FOUND);
    }
    @GetMapping ("/view/{id}")
    public ResponseEntity<Driver> viewDriverByIdHandler(@PathVariable("id") Integer driverId ){
        log.info("Class: DriverController, method: viewDriverByIdHandler started");
        Driver driver= driverService.viewDriver(driverId);
        log.info("Class: DriverController, method: viewDriverByIdHandler returned "+driver);
        return new ResponseEntity<>(driver, HttpStatus.FOUND);
    }

}
