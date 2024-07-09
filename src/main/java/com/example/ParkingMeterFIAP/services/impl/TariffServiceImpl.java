package com.example.ParkingMeterFIAP.services.impl;

import com.example.ParkingMeterFIAP.controller.exception.ControllerNotFoundException;
import com.example.ParkingMeterFIAP.models.Tariff;
import com.example.ParkingMeterFIAP.repository.TariffRepository;
import com.example.ParkingMeterFIAP.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public ResponseEntity<?> save(Tariff tariff) {
        tariffRepository.save(tariff);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Tariff findByfinalTermIsNull() {
        Tariff tariff = tariffRepository.findByfinalTermIsNull()
                .orElseThrow( () ->
                        new ControllerNotFoundException("Tariff not Existing"));
        return tariff;
    }
}
