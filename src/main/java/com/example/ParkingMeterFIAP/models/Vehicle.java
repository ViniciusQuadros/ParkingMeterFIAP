package com.example.ParkingMeterFIAP.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String licensePlate;

    private String brand;

    private String model;

    private String color;

}
