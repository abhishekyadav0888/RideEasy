package com.rideeasy.repository;

import com.rideeasy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Optional<Customer> findByuserName(String username);

    @Query("Select c from Customer c where c.isDeleted=false")
    public List<Customer> getAllCustomers();


}
