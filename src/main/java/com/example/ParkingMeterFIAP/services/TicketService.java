package com.example.ParkingMeterFIAP.services;

import com.example.ParkingMeterFIAP.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {
    public ResponseEntity<?> save(Ticket ticket);
    public void validatePaymentMethodVersusTypeParking(Ticket ticket);
    public void calcTicket(Ticket ticket);
    public void paymentIntegration(Ticket ticket);
    public Ticket findById(String id);
    public Page<?> notifyTicket(Pageable pageable);
}
