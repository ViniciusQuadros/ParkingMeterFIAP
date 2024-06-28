package com.example.ParkingMeterFIAP.controller;

import com.example.ParkingMeterFIAP.models.Conductor;
import com.example.ParkingMeterFIAP.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ParkingMeter")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @PostMapping
    public ResponseEntity<Conductor> save(@RequestBody Conductor conductor)
    {
        conductorService.save(conductor);
        return new ResponseEntity<>(conductor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Conductor>> findAll(Pageable pageable){
        Page<Conductor> conductors = (Page<Conductor>) conductorService.findAll(pageable);
        return ResponseEntity.ok(conductors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conductor> findById(@PathVariable String id) {
        Conductor conductor = conductorService.findById(id);
        return ResponseEntity.ok(conductor);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        conductorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conductor> update(@PathVariable String id, @RequestBody Conductor conductor){
        conductorService.update(id, conductor);
        return ResponseEntity.ok(conductor);
    }

}
