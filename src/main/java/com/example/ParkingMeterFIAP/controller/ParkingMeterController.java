package com.example.ParkingMeterFIAP.controller;

import com.example.ParkingMeterFIAP.models.Conductor;
import com.example.ParkingMeterFIAP.services.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ParkingMeter")
public class ParkingMeterController {

    @Autowired
    private ParkingMeterService service;

    @PostMapping
    public ResponseEntity<Conductor> createConductor(@RequestBody Conductor conductor){
        return null;
    }



}
