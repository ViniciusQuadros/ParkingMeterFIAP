package com.example.ParkingMeterFIAP.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError{
    private List<ValidateMessage> messages = new ArrayList<ValidateMessage>();

    public List<ValidateMessage> getMessages() {
        return messages;
    }

    public void addMensagens(String field, String message) {
        messages.add(new ValidateMessage(field, message));
    }

}
