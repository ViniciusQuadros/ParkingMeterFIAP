package com.example.ParkingMeterFIAP.services;

import com.example.ParkingMeterFIAP.models.Conductor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ConductorService {
    public ResponseEntity<?> save(Conductor conductor);
    public Page<?> findAll(Pageable pageable);
    public void deleteById(String id);
    public ResponseEntity<?> update(String id, Conductor conductor);
    public Conductor findById(String id);
}
