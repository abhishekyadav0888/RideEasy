<<<<<<< HEAD
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
=======
package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Entity.Customer;
import com.masai.Exception.Nullexception;
import com.masai.Service.CustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping(value = "/save",consumes = "application/json")
	public Customer SaveCustomerDetails(@Valid @RequestBody Customer customer)
	{	System.out.println(customer);
		return service.saveCustomer(customer);
	}
	@PutMapping("/update/{id}")
	public Customer updateCustomerDetails(@Valid @RequestBody Customer customer,@PathVariable("id")Integer id )
	{
		
		return service.updateCustomer(customer,id);
	}
    @DeleteMapping("/customer/delete/{id}")
	public String deleteCustomerDetails(@PathVariable("id")Integer id) {
		
    	return service.deleteCustomer(id);
	}
    
	@GetMapping("/customer/{Id}")
	public Customer getCustomerDetails(@PathVariable("Id") Integer id)
	{    
		return service.findCustomer(id);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomerDetails() throws Nullexception{
		List<Customer> customers=service.getAllCustomerDetails();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/customer/{Email}/{Password}")
	public Customer validCustomerDetailsUsingEmailAndPassword(@PathVariable("Email") String Email,@PathVariable("Password")String Password)
	{    
		return service.vaildCustomer(Email, Password);
	}
	
	
}

>>>>>>> 796a0b533dc7c9606de634342262bac73fc3c69f
