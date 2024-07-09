package com.example.ParkingMeterFIAP.models.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT("CREDIT", TypeParking.NONE),
    DEBIT("DEBIT", TypeParking.NONE),
    PIX("PIX", TypeParking.VARIABLE);

    private final String paymentMethod;
    private final TypeParking restrictionTypeParking;

    PaymentMethod(String paymentMethod, TypeParking restrictionTypeParking) {
        this.paymentMethod = paymentMethod;
        this.restrictionTypeParking = restrictionTypeParking;
    }
}
