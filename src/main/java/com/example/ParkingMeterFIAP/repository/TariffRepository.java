package com.example.ParkingMeterFIAP.repository;

import com.example.ParkingMeterFIAP.models.Tariff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TariffRepository extends MongoRepository<Tariff, String> {
    Optional<Tariff> findByfinalTermIsNull();
}
