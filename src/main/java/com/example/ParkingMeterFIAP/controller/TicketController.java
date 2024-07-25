package com.example.ParkingMeterFIAP.controller;

import com.example.ParkingMeterFIAP.models.Ticket;
import com.example.ParkingMeterFIAP.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ParkingMeter/Ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> save(@RequestBody Ticket ticket)
    {
        ticketService.save(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable String id) {
        Ticket ticket = ticketService.findById(id);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable String id){
        Ticket ticket = ticketService.findById(id);
        ticketService.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    
    @GetMapping("/notify")
    public ResponseEntity<Page<Ticket>> notifyTicket(Pageable pageable){
        Page<Ticket> tickets = (Page<Ticket>) ticketService.notifyTicket(pageable);
        return ResponseEntity.ok(tickets);
    }

}
