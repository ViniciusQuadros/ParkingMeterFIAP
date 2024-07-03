package com.example.ParkingMeterFIAP.controller.exception;

public class ValidateMessage {

    private String field;
    private String message;

    public ValidateMessage(){}

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidateMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
