package com.example.ParkingMeterFIAP.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Document
public class Tariff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDate initialTerm;

    private LocalDate  finalTerm;

    private Double tariff;

}
