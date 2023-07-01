package com.rideeasy.controller;

import com.rideeasy.model.Driver;
import com.rideeasy.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private Driver requestDriver;
    private Driver responseDriver;

    @BeforeEach
    public void init(){
        // requestDriver
        requestDriver= new Driver();
//        name, username, password, address, mobilenumber, email, role, isDeleted, licenceNumber, rating , isAvailable
        requestDriver.setName("Vikash");
        requestDriver.setUserName("vikash123");
        requestDriver.setPassword("vikash123");
        requestDriver.setAddress("delhi");
        requestDriver.setMobileNumber("8574962513");
        requestDriver.setEmail("vikash@gmail.com");
        requestDriver.setRole("ROLE_DRIVER");
        requestDriver.setIsDeleted(false);
        requestDriver.setLicenceNumber("AB-1234567890123");
        requestDriver.setRating(4.5f);
        requestDriver.setIsAvailable(true);

        // responseDriver
        responseDriver= new Driver();
        responseDriver.setDriverId(10);
        responseDriver.setName("Vikash");
        responseDriver.setUserName("vikash123");
        responseDriver.setPassword("vikash123");
        responseDriver.setAddress("delhi");
        responseDriver.setMobileNumber("8574962513");
        responseDriver.setEmail("vikash@gmail.com");
        responseDriver.setRole("ROLE_DRIVER");
        responseDriver.setIsDeleted(false);
        responseDriver.setLicenceNumber("AB-1234567890123");
        responseDriver.setRating(4.5f);
        responseDriver.setIsAvailable(true);
    }

    @Test
    public void testAddDriverHandler_whenValidDetailsProvided_thenReturnRegisteredDriver() throws Exception{
        // Arrange
        Mockito.when(driverService.insertDriver(any(Driver.class))).thenReturn(responseDriver);

    }
}
