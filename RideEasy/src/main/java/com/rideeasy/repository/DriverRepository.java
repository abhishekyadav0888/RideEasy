package com.rideeasy.repository;

import com.rideeasy.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Integer> , PagingAndSortingRepository<Driver,Integer> {

    public Optional<Driver> findByUserName(String userName);

    public Optional<Driver> findByMobileNumber(String mobileNumber);

    public Optional<Driver> findByEmail(String email);

    public List<Driver> findByRatingGreaterThanOrEqual(Float rating);

}
