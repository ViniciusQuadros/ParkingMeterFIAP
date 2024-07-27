package com.example.ParkingMeterFIAP.models;

import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import com.example.ParkingMeterFIAP.models.enums.ParkingType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String conductorId;

    private String licensePlate;

    private ParkingType parkingType;

    private LocalDateTime initialDateTime;

    private LocalDateTime finalDateTime;

    private LocalDateTime notificationDateTime;

    private Integer usageTime;

    private PaymentMethod paymentMethod;

    private Double paymentAmount;

    private String tariffId;

    private Double tariff;

}
