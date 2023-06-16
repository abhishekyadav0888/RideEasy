package com.rideeasy.service;

import com.rideeasy.model.Cab;

import java.util.List;

public interface CabService {

    public Cab insertCab(Cab cab);

    public Cab updateCab(Cab cab);

    public Cab deleteCab(Integer cabId);

    public List<Cab> viewCabsOfType(String carType);

    public Integer countCabsOfType(String carType);
}
