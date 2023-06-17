package com.rideeasy.service;

import com.rideeasy.model.Admin;
import com.rideeasy.model.Customer;
import com.rideeasy.model.Driver;
import com.rideeasy.repository.AdminRepository;
import com.rideeasy.repository.CustomerRepository;
import com.rideeasy.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {


    //    create the object of repository here
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DriverRepository driverRepository;




    /**
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("CUST_")) {
            // Remove the prefix to get the actual username
            String actualUsername = username.substring(5);
            Optional<Customer> opt = customerRepository.findByuserName(actualUsername);
            if (opt.isPresent()) {
                Customer customer= opt.get();

                List<GrantedAuthority> authorities= new ArrayList<>();
                SimpleGrantedAuthority sga= new SimpleGrantedAuthority(customer.getRole());
                authorities.add(sga);

                return new User(
                        customer.getUserName(),
                        customer.getPassword(),
                        authorities
                );
            }
        } else if (username.startsWith("ADMIN_")) {
            // Remove the prefix to get the actual username
            String actualUsername = username.substring(6);
            Optional<Admin> opt = adminRepository.findByUserName(actualUsername);
            if (opt.isPresent()) {
                Admin admin= opt.get();

                List<GrantedAuthority> authorities= new ArrayList<>();
                SimpleGrantedAuthority sga= new SimpleGrantedAuthority(admin.getRole());
                authorities.add(sga);

                return new User(
                        admin.getUserName(),
                        admin.getPassword(),
                        authorities
                );
            }
        } else if (username.startsWith("DRIVER_")) {
            // Remove the prefix to get the actual username
            String actualUsername = username.substring(7);
            Optional<Driver> opt = driverRepository.findByUserName(actualUsername);
            if (opt.isPresent()) {
                Driver driver= opt.get();

                List<GrantedAuthority> authorities= new ArrayList<>();
                SimpleGrantedAuthority sga= new SimpleGrantedAuthority(driver.getRole());
                authorities.add(sga);

                return new User(
                        driver.getUserName(),
                        driver.getPassword(),
                        authorities
                );
            }
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }


}
