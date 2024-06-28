package com.example.ParkingMeterFIAP.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;

    private String number;

    private String city;

    private String state;

    private String zipCode;

    private String complement;

}
