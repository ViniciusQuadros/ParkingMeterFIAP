package com.example.ParkingMeterFIAP.repository;

import com.example.ParkingMeterFIAP.models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
}
