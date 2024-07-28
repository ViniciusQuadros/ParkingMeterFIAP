package com.example.ParkingMeterFIAP.models.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT("CREDIT", ParkingType.NONE),
    DEBIT("DEBIT", ParkingType.NONE),
    PIX("PIX", ParkingType.VARIABLE);

    private final String paymentMethod;
    private final ParkingType restrictionParkingType;

    PaymentMethod(String paymentMethod, ParkingType restrictionParkingType) {
        this.paymentMethod = paymentMethod;
        this.restrictionParkingType = restrictionParkingType;
    }
}
