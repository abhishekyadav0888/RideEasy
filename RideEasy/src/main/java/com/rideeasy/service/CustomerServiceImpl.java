package com.rideeasy.service;

import com.rideeasy.exception.InvalidInputException;
import com.rideeasy.exception.NotFoundException;
import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Customer;
import com.rideeasy.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer addNewCustomer(Customer customer) {
        customer.setIsDeleted(false);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("New Customer added successful : CustomerService");
        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.setIsDeleted(false);
        Optional<Customer> opt = customerRepository.findById(customer.getCustomerId());
       if(opt.isPresent()){
            Customer updatedCustomer = customerRepository.save(customer);
           log.info("Updated Customer successful : CustomerService");
            return updatedCustomer;
       }else {
           throw new InvalidInputException("Invalid Customer Id");
       }
    }

    @Override
    public Customer deleteCustomer(Integer customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        log.info("try to delete customer");
        if(opt.isPresent()){
            Customer customer = opt.get();
            customer.setIsDeleted(true);
            customerRepository.save(customer);
            log.info("Customer Deleted successful : CustomerService");
            return customer;
        }else {
            throw new InvalidInputException("Invalid Customer Id");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = customerRepository.getAllCustomers();
        if(customerList.isEmpty()){
            throw new  NotFoundException("There is no customer Available");
        }else{
            log.info("get All Customers successful : CustomerService");
            return customerList;
        }
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if(opt.isPresent()){
            Customer customer = opt.get();
            log.info("get customer by id successful : CustomerService");
            return customer;
        }else {
            throw new InvalidInputException("Invalid Customer Id");
        }
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        Optional<Customer> opt = customerRepository.findByuserName(username);
        if(opt.isPresent()){
            Customer customer = opt.get();
            if(customer.getPassword().equals(password)){
                if(customer.getIsDeleted()==false){
                    log.info("Customer Validated successful : CustomerService");
                    return customer;
                }else{
                    throw new RideEasyException("This user is Deleted");
                }

            }else {
                throw new InvalidInputException("Password is Invalid");
            }
        }else{
            throw new NotFoundException("Invalid Username");
        }

    }

    @Override
    public Customer unBlockCustomer(Integer customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if(opt.isPresent()){
            Customer customer = opt.get();
            customer.setIsDeleted(false);
            customerRepository.save(customer);
            log.info("Un Block Customer successful : CustomerService");
            return customer;
        }else {
            throw new InvalidInputException("Invalid Customer Id");
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Optional<Customer> opt = customerRepository.findByuserName(username);
        if(opt.isPresent()){
            Customer customer = opt.get();
            customerRepository.save(customer);
            log.info("Un Block Customer successful : CustomerService");
            if(customer.getIsDeleted()==true){
                throw new NotFoundException("User is Blocked");
            }
            return customer;
        }else {
            throw new InvalidInputException("Invalid Customer Username");
        }
    }
}
