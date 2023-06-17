package com.rideeasy.service;

import com.rideeasy.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer addNewCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);

    public Customer deleteCustomer(Integer customerId);

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Integer customerId);

    public Customer validateCustomer(String username,String password);

    public Customer unBlockCustomer(Integer customerId);

    public Customer getCustomerByUsername(String username);
}
