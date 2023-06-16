package com.rideeasy.repository;

import com.rideeasy.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab, Integer> , PagingAndSortingRepository<Cab, Integer> {

    public List<Cab> findByCarType(String carType);

    public Integer countByCarType(String carType);

}
