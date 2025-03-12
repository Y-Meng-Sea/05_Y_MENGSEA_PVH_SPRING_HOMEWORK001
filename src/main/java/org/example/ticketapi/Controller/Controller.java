package org.example.ticketapi.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.ticketapi.model.entity.ApiRespones;
import org.example.ticketapi.model.entity.Ticket;
import org.example.ticketapi.model.entity.TicketStatus;
import org.example.ticketapi.model.request.RequestTicket;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class Controller {
    ApiRespones<Ticket> respones = null;
    List<Ticket> tickets = new ArrayList<>();

    Controller(){
        tickets.add(new Ticket(1,"Mengsea",LocalDate.now(),"PP","PPT",22.5,false, TicketStatus.CANCELLED,"002A"));
        tickets.add(new Ticket(2,"Shoko",LocalDate.now(),"PP","PPT",22.5,false, TicketStatus.COMPLETED,"002A"));

    }

    // get All ticket {pagination not yet implement}
    @GetMapping
    @Operation(summary = "Get all tickets")
    public ResponseEntity<ApiRespones<List<Ticket>>> tickets(){
        ApiRespones<List<Ticket>> respones = new ApiRespones<>(true,"All tickets retrieved successfully.",HttpStatus.OK,tickets,LocalDate.now());
        return ResponseEntity.ok(respones);
    }

    // create ticket
    @PostMapping
    @Operation(summary = "Create a new tickets")
    public ResponseEntity<ApiRespones<Ticket>> addTicket(@RequestBody Ticket newTicket){
        if(newTicket.getPrice()>0){
            tickets.add(newTicket);
        }
        respones = new ApiRespones<>(true, "Create ticket successfully", HttpStatus.OK,newTicket,LocalDate.now());
        return ResponseEntity.ok(respones);

    }

    // get by ID
    @GetMapping("/{ticket-id}")
    @Operation(summary = "Get a ticket by ID")
    public ResponseEntity<ApiRespones<Ticket>> getTicketById(@PathVariable("ticket-id")  int ticketId ){
        for(Ticket ticket : tickets){
            if (ticket.getTicketId() == ticketId){
                respones = new ApiRespones<>(true, "Create ticket successfully", HttpStatus.OK,ticket,LocalDate.now());

            }
        }
        return ResponseEntity.ok(respones);
    }

    // update an existing ticket by id
    @PutMapping("/{ticket-id}")
    @Operation(summary = "Update an existing ticket by ID")
    public ResponseEntity<ApiRespones<Ticket>> updateTicketById(@PathVariable("ticket-id") int updateId, @RequestBody RequestTicket requestTicket ){
        for(Ticket ticket: tickets){
            if(ticket.getTicketId() == updateId){
                ticket.setPassengerName(requestTicket.getPassengerName());
                ticket.setTravelDate(requestTicket.getTravelDate());
                ticket.setSourceStation(requestTicket.getSourceStation());
                ticket.setDestinationStation(requestTicket.getDestinationStation());
                ticket.setPrice(requestTicket.getPrice());
                ticket.setPaymentStatus(requestTicket.isPaymentStatus());
                ticket.setTicketStatus(requestTicket.getTicketStatus());
                ticket.setSeatNumber(requestTicket.getSeatNumber());
                respones = new ApiRespones<>(true,"success",HttpStatus.OK,ticket,LocalDate.now());
            }
        }
        return ResponseEntity.ok(respones);
    }

    //delete ticket
    @DeleteMapping("/{ticket-id}")
    @Operation(summary = "Delete a ticket by ID")
    public ResponseEntity<ApiRespones<Ticket>> deleteTicket(@PathVariable("ticket-id") int deleteId){
        for(Ticket ticket : tickets){
            if(ticket.getTicketId() == deleteId){
                tickets.remove(ticket);
                respones = new ApiRespones<>(true, "Ticket deleted successfully",HttpStatus.OK,ticket,LocalDate.now());
                break;
            }else{
                respones = new ApiRespones<>(false, "No ticket found with ID: " + deleteId ,HttpStatus.NOT_FOUND,LocalDate.now());
            }
        }
        return ResponseEntity.ok(respones);
    }


    // search by name
    @GetMapping("/search")
    @Operation(summary = "Search ticket by passenger name")
    public ResponseEntity<ApiRespones<Ticket>> searchByName(@RequestParam String name){
        for(Ticket ticket : tickets){
            if(ticket.getPassengerName().toLowerCase().contains(name.toLowerCase())){
                return ResponseEntity.ok(respones);

            }
        }
        return null;
    }


    // filter by ticket status and travel date
    @GetMapping("/filter")
    @Operation(summary = "Filter ticket by status and travel")
    public ResponseEntity<ApiRespones<Ticket>> filter(@RequestParam("ticketStatus") TicketStatus ticketStatus, @RequestParam("travelDate") LocalDate travelDate){
        for (Ticket ticket : tickets){
            if (ticket.getTicketStatus().equals(ticketStatus) && ticket.getTravelDate().equals(travelDate)){
                return ResponseEntity.ok(respones);
            }
        }
        return null;
    }

}
