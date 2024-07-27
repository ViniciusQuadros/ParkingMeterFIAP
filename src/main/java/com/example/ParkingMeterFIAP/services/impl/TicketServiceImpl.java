package com.example.ParkingMeterFIAP.services.impl;

import com.example.ParkingMeterFIAP.controller.exception.ControllerNotFoundException;
import com.example.ParkingMeterFIAP.models.Tariff;
import com.example.ParkingMeterFIAP.models.Ticket;
import com.example.ParkingMeterFIAP.models.enums.ParkingType;
import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import com.example.ParkingMeterFIAP.repository.TariffRepository;
import com.example.ParkingMeterFIAP.repository.TicketRepository;
import com.example.ParkingMeterFIAP.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public ResponseEntity<?> save(Ticket ticket) {
        if (ticket.getId() == null || (ticket.getParkingType() == ParkingType.VARIABLE && ticket.getFinalDateTime() == null)) {
            validatePaymentMethodVersusTypeParking(ticket);
            calcTicket(ticket);
            paymentIntegration(ticket);
            ticketRepository.save(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            throw new IllegalArgumentException("Update not allowed for stop type");
        }
    }

    @Override
    public void validatePaymentMethodVersusTypeParking(Ticket ticket) {
        PaymentMethod paymentMethod = ticket.getPaymentMethod();
        if (paymentMethod.getRestrictionParkingType() != ParkingType.NONE) {
            throw new IllegalArgumentException("Payment method restricted by stop type");
        }
    }

    @Override
    public void calcTicket(Ticket ticket) {
        if (ticket.getTariffId() == null) {
            Optional<Tariff> tariff = tariffRepository.findByfinalTermIsNull();
            ticket.setTariff(tariff.get().getTariff());
            ticket.setTariffId(tariff.get().getId());
        }

        if(ticket.getParkingType().equals(ParkingType.FIX)){
            if(ticket.getUsageTime() != null) {
                ticket.setInitialDateTime(LocalDateTime.now().withSecond(0).withNano(0));
                ticket.setFinalDateTime(ticket.getInitialDateTime().plusHours(ticket.getUsageTime()));
            }else{
                throw new IllegalArgumentException("Mandatory time parameter");
            }
        }else{
            if(ticket.getInitialDateTime() == null){
                ticket.setInitialDateTime(LocalDateTime.now().withSecond(0).withNano(0));
            }else{
                ticket.setFinalDateTime(LocalDateTime.now().withSecond(0).withNano(0));
                long difMinutes = ticket.getInitialDateTime().until(ticket.getFinalDateTime(), ChronoUnit.MINUTES);
                long hours = (long) Math.ceil((double) difMinutes / 60);
                ticket.setUsageTime((int) hours);
            }
        }

        if(ticket.getUsageTime() !=  null){
            ticket.setPaymentAmount(ticket.getTariff() * ticket.getUsageTime());
        }
    }

    @Override
    public void paymentIntegration(Ticket ticket) {
    }

    @Override
    public Ticket findById(String id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow( () ->
                        new ControllerNotFoundException("Ticket not Existing"));
        return ticket;
    }

    @Override
    public List<Ticket> notifyTicket(){
        List<Ticket> allTickets = ticketRepository.findAll();

        allTickets.forEach(ticket -> {
            if(ticket.getParkingType().equals(ParkingType.FIX)){
                calculateFixWarningTime(ticket);
            }
            if(ticket.getParkingType().equals(ParkingType.VARIABLE) && ticket.getFinalDateTime() == null){
                calculateVariableWarningType(ticket);
            }
        });
        return allTickets;
    }

    private static void calculateFixWarningTime(Ticket ticket) {
        ticket.setNotificationDateTime(ticket.getFinalDateTime().minusMinutes(10));
    }

    private static void calculateVariableWarningType(Ticket ticket) {
        long consumedHours = ChronoUnit.HOURS.between(ticket.getInitialDateTime(), LocalTime.now());
        ticket.setNotificationDateTime(ticket.getInitialDateTime().plusHours(consumedHours).plusMinutes(50));
    }
}
