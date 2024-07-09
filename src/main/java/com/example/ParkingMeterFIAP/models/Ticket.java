package com.example.ParkingMeterFIAP.models;

import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import com.example.ParkingMeterFIAP.models.enums.TypeParking;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String conductorId;

    private String licensePlate;

    private TypeParking typeParking;

    private LocalDate date;

    private LocalTime initialHour;

    private LocalTime finalHour;

    private Integer time;

    private PaymentMethod paymentMethod;

    private Double amount;

    private String tariffId;

    private Double tariff;

}
