package com.example.ParkingMeterFIAP.models;

import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private String cpf;

    private String email;

    private String cel;

    private Address address;

    private List<Vehicle> vehicles;

    private PaymentMethod paymentMethod;

}
