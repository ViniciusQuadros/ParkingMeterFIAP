package com.example.ParkingMeterFIAP.services.impl;

import com.example.ParkingMeterFIAP.controller.exception.ControllerNotFoundException;
import com.example.ParkingMeterFIAP.models.Conductor;
import com.example.ParkingMeterFIAP.repository.ConductorRepository;
import com.example.ParkingMeterFIAP.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public ResponseEntity<?> save(Conductor conductor) {
        conductorRepository.save(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Page<?> findAll(Pageable pageable) {
        Sort sort = Sort.by("name").ascending();
        Pageable pagination =
                PageRequest.of(pageable.getPageNumber(),
                        pageable.getPageSize(), sort);

        return conductorRepository.findAll(pagination);
    }

    @Override
    public void deleteById(String id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Conductor not Existing"));

        conductorRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> update(String id, Conductor conductor) {
        Conductor conductorExisting = conductorRepository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Conductor not Existing"));

        conductorExisting.setName(conductor.getName());
        conductorExisting.setCpf(conductor.getCpf());
        conductorExisting.setEmail(conductor.getEmail());
        conductorExisting.setCel(conductor.getCel());
        conductorExisting.setAddress(conductor.getAddress());
        conductorExisting.setVehicles(conductor.getVehicles());
        conductorExisting.setPaymentMethod(conductor.getPaymentMethod());
        conductorRepository.save(conductorExisting);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public Conductor findById(String id) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Conductor not Existing"));
        return conductor;
    }

}
