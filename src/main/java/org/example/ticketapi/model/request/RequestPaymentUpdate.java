package org.example.ticketapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestPaymentUpdate {
    private int ticketId;
    private boolean paymentStatus;
}
