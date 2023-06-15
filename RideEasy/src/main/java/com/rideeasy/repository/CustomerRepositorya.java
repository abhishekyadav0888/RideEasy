package com.rideeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rideeasy.model.Customer;

public interface CustomerRepositorya extends JpaRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer> {

}
