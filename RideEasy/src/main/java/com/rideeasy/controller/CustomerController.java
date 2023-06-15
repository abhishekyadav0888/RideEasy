package com.rideeasy.controller;

import com.rideeasy.model.Customer;
import com.rideeasy.service.CustomerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @PostMapping("/customers")
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody Customer customer){
        log.info("Try to add new Customer : CustomerController");
        Customer savedCustomer = customerService.addNewCustomer(customer);
        log.info("Customer added successful : CustomerController");
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> updateExistingCustomer(@Valid @RequestBody Customer customer){
        log.info("Try to add update Customer : CustomerController");
        Customer updateCustomer = customerService.updateCustomer(customer);
        log.info("Customer updated successful : CustomerController");
        return new ResponseEntity<>(updateCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Customer> deleteExistingCustomer(@PathVariable Integer customerId){
        log.info("Try to add delete Customer : CustomerController");
       Customer deletedCustomer = customerService.deleteCustomer(customerId);
        log.info("Deleted Customer Successful : CustomerController");
        return new ResponseEntity<>(deletedCustomer,HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        log.info("Try to get All Customers : CustomerController");
        List<Customer> customerList = customerService.getAllCustomers();
        log.info("get All Customers Successful : CustomerController");
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId){
        log.info("Try to get Customer : CustomerController");
        Customer customer = customerService.getCustomerById(customerId);
        log.info("fetched Customer Successful : CustomerController");
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PostMapping("/customers/{username}/{password}")
    public ResponseEntity<Customer> validationByUserNameAndPassword(@PathVariable String username,@PathVariable String password){
        log.info("Try to Validate Customer : CustomerController");
        Customer validatedCustomer = customerService.validateCustomer(username,password);
        log.info("Validation of Customer Successful : CustomerController");
        return new ResponseEntity<>(validatedCustomer,HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> unblockCustomerById(@PathVariable Integer customerId){
        log.info("Try to Unblock Customer : CustomerController");
        Customer unBlockCustomer = customerService.unBlockCustomer(customerId);
        log.info("UnBlock Customer Successful : CustomerController");
        return new ResponseEntity<>(unBlockCustomer,HttpStatus.OK);
    }



}
