package com.example.ParkingMeterFIAP.repository;

import com.example.ParkingMeterFIAP.models.Conductor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends MongoRepository<Conductor, String> {
    public Page<Conductor> findAll(Pageable pageable);
}
