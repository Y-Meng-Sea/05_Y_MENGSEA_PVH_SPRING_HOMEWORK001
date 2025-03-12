package org.example.ticketapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiRespones<T> {
    private boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private LocalDate timestamp;
    public ApiRespones(boolean success, String message, HttpStatus status, LocalDate timestamp) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
