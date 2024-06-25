package com.example.ParkingMeterFIAP.models;

import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String cpf;

    private String email;

    private String cel;

    private Address address;

    private List<Vehicle> vehicles;

    private PaymentMethod paymentMethod;

}
