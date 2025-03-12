package org.example.ticketapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ticketapi.model.entity.TicketStatus;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
public class RequestTicket {
    private String passengerName;
    private LocalDate travelDate;
    private String sourceStation;
    private String destinationStation;
    private double price;
    private boolean paymentStatus;
    private TicketStatus ticketStatus;
    private String seatNumber;
}
