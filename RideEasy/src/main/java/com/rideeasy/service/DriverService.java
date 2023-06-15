package com.rideeasy.service;

import com.rideeasy.model.Driver;

import java.util.List;

public interface DriverService {

    public Driver insertDriver(Driver driver);

    public Driver updateDriver(Driver driver);

    public Driver deleteDriver(Integer driverId);

    public List<Driver> viewBestDrivers();

    public Driver viewDriver(Integer driverId);

}
