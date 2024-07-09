package com.example.ParkingMeterFIAP.services;

import com.example.ParkingMeterFIAP.models.Tariff;
import org.springframework.http.ResponseEntity;

public interface TariffService {
    public ResponseEntity<?> save(Tariff tariff);
    public Tariff findByfinalTermIsNull();
}
