package com.example.ParkingMeterFIAP.services.impl;

import com.example.ParkingMeterFIAP.controller.exception.ControllerNotFoundException;
import com.example.ParkingMeterFIAP.models.Tariff;
import com.example.ParkingMeterFIAP.models.Ticket;
import com.example.ParkingMeterFIAP.models.enums.PaymentMethod;
import com.example.ParkingMeterFIAP.models.enums.TypeParking;
import com.example.ParkingMeterFIAP.repository.TariffRepository;
import com.example.ParkingMeterFIAP.repository.TicketRepository;
import com.example.ParkingMeterFIAP.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (ticket.getId() == null || (ticket.getTypeParking() == TypeParking.VARIABLE && ticket.getFinalHour() == null)) {
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
        if (paymentMethod.getRestrictionTypeParking() != TypeParking.NONE) {
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

        if(ticket.getTypeParking().equals(TypeParking.FIX)){
            if(ticket.getTime() != null) {
                ticket.setDate(LocalDate.now());
                ticket.setInitialHour(LocalTime.now().withSecond(0).withNano(0));
                ticket.setFinalHour(ticket.getInitialHour().plusHours(ticket.getTime()));
            }else{
                throw new IllegalArgumentException("Mandatory time parameter");
            }
        }else{
            if(ticket.getInitialHour() == null){
                ticket.setDate(LocalDate.now());
                ticket.setInitialHour(LocalTime.now().withSecond(0).withNano(0));
            }else{
                ticket.setFinalHour(LocalTime.now().withSecond(0).withNano(0));
                long difMinutes = ticket.getInitialHour().until(ticket.getFinalHour(), ChronoUnit.MINUTES);
                long hours = (long) Math.ceil((double) difMinutes / 60);
                ticket.setTime((int) hours);
            }
        }

        if(ticket.getTime() !=  null){
            ticket.setAmount(ticket.getTariff() * ticket.getTime());
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
            if(ticket.getTypeParking().equals(TypeParking.FIX)){
                calculateFixWarningTime(ticket);
            }
            if(ticket.getTypeParking().equals(TypeParking.VARIABLE)){
                calculateVariableWarningType(ticket);
            }
        });
        return allTickets;
    }

    private static void calculateFixWarningTime(Ticket ticket) {
        ticket.setWarningTime(ticket.getFinalHour().minusMinutes(10));
    }

    private static void calculateVariableWarningType(Ticket ticket) {
        long consumedHours = ChronoUnit.HOURS.between(ticket.getInitialHour(), LocalTime.now());
        ticket.setWarningTime(ticket.getInitialHour().plusHours(consumedHours).plusMinutes(50));
    }
}
