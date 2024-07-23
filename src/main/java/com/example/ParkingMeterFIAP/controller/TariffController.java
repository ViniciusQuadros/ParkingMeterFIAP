package com.example.ParkingMeterFIAP.controller;

import com.example.ParkingMeterFIAP.models.Tariff;
import com.example.ParkingMeterFIAP.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ParkingMeter/Tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @PostMapping
    public ResponseEntity<Tariff> save(@RequestBody Tariff tariff)
    {
        Tariff currentTariff = tariffService.findByfinalTermIsNull();
        if (currentTariff != null){
            currentTariff.setFinalTerm(LocalDate.now());
            tariffService.save(currentTariff);
        }

        tariffService.save(tariff);
        return new ResponseEntity<>(tariff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Tariff> findByfinalTermIsNull() {
        Tariff tariff = tariffService.findByfinalTermIsNull();
        return ResponseEntity.ok(tariff);
    }

}
