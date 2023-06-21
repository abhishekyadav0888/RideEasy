package com.rideeasy.appconfig;

import com.rideeasy.model.Admin;
import com.rideeasy.model.Customer;
import com.rideeasy.model.Driver;
import com.rideeasy.repository.AdminRepository;
import com.rideeasy.repository.CustomerRepository;
import com.rideeasy.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PasswordEncoder pEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Out Authentication Provider is used...");

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        // Check the user type based on the prefix in the username
        if (username.startsWith("CUST_")) {
            String customerUsername = username.substring(5); // Remove the "CUST_" prefix
            return authenticateCustomer(customerUsername, pwd);
        } else if (username.startsWith("ADMIN_")) {
            String adminUsername = username.substring(6); // Remove the "ADMIN_" prefix
            return authenticateAdmin(adminUsername, pwd);
        } else if (username.startsWith("DRIVER_")) {
            String driverUsername = username.substring(7); // Remove the "DRIVER_" prefix
            return authenticateDriver(driverUsername, pwd);
        } else {
            throw new BadCredentialsException("Invalid username format");
        }
    }

    private Authentication authenticateCustomer(String username, String password) {
        Optional<Customer> opt = customerRepository.findByUserName(username);
        if (opt.isEmpty()) {
            throw new BadCredentialsException("No user registered with this username");
        }

        Customer customer = opt.get();
        if (pEncoder.matches(password, customer.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    private Authentication authenticateAdmin(String username, String password) {
        Optional<Admin> opt = adminRepository.findByUserName(username);
        if (opt.isEmpty()) {
            throw new BadCredentialsException("No user registered with this username");
        }

        Admin admin = opt.get();
        if (pEncoder.matches(password, admin.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(admin.getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    private Authentication authenticateDriver(String username, String password) {
        Optional<Driver> opt = driverRepository.findByUserName(username);
        if (opt.isEmpty()) {
            throw new BadCredentialsException("No user registered with this username");
        }

       Driver driver = opt.get();
        if (pEncoder.matches(password, driver.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(driver.getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
