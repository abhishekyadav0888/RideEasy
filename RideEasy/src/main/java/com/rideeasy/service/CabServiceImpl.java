package com.rideeasy.service;

import com.rideeasy.exception.RideEasyException;
import com.rideeasy.model.Cab;
import com.rideeasy.repository.CabRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CabServiceImpl implements CabService{

    @Autowired
    private CabRepository cabRepository;


    /**
     * @param cab
     * @return persistedCab
     * Take cab to be persisted, returns persisted cab.
     */
    @Override
    public Cab insertCab(Cab cab) {
        log.info("Class: CabServiceImpl, method: insertCab started");
        // checking for cab already exists or not;
        Optional<Cab> opt=  cabRepository.findById(cab.getCabId());
        if(opt.isPresent()) throw new RideEasyException("Cab with id: "+cab.getCabId()+" already exists ");
        // if cab does not exist already, then saving the cab;
        Cab persistedCab= cabRepository.save(cab);
        log.info("Class: CabServiceImpl, method: insertCab returned "+persistedCab);
        return persistedCab;

    }

    /**
     * @param cab
     * @return updatedCab
     * Take cab to be updated, returns updated cab.
     */
    @Override
    public Cab updateCab(Cab cab) {
        log.info("Class: CabServiceImpl, method: updateCab started");
        // checking for cab exists or not;
         Cab existingCab= cabRepository.findById(cab.getCabId()).orElseThrow(()-> new RideEasyException("Cab with id: "+cab.getCabId()+" does not exist "));
         //updating the cab; Note save method act as both, save and update.;
         Cab updatedCab=cabRepository.save(cab);
        log.info("Class: CabServiceImpl, method: updateCab returned "+updatedCab);
        return updatedCab;
    }

    /**
     * @param cabId
     * @return deletedCab
     * Take cabId of the cab to be deleted, returns deleted cab.
     */
    @Override
    public Cab deleteCab(Integer cabId) {
        log.info("Class: CabServiceImpl, method: deleteCab started");
        // checking for cab exists or not;
        Cab deletingCab= cabRepository.findById(cabId).orElseThrow(()-> new RideEasyException("Cab with id: "+cabId+" does not exist "));
        cabRepository.deleteById(cabId);
        log.info("Class: CabServiceImpl, method: deleteCab returned "+deletingCab);
        return deletingCab;
    }

    /**
     * @param carType
     * @return List of cabs
     * Take car type to view the list of cabs, returns list of cabs.
     */
    @Override
    public List<Cab> viewCabsOfType(String carType) {
        log.info("Class: CabServiceImpl, method: viewCabsOfType started");
        List<Cab> cabs= cabRepository.findByCarType(carType);
        if(cabs.isEmpty()) throw new RideEasyException("No cab found of type "+carType);
        log.info("Class: CabServiceImpl, method: viewCabsOfType returned list of cabs of type "+carType);
        return cabs;
    }


    /**
     * @param carType
     * @return
     */
    @Override
    public Integer countCabsOfType(String carType) {
        log.info("Class: CabServiceImpl, method: countCabsOfType started");
        Integer count= (Integer) cabRepository.countByCarType(carType);
        log.info("Class: CabServiceImpl, method: countCabsOfType returned "+count);
        return count;
    }
}
