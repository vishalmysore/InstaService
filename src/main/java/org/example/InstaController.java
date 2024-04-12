package org.example;



import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Log
@RestController
@RequestMapping("/api/tickets")
public class InstaController {
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Ticket> tickets = new HashMap<>();
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        long id = counter.incrementAndGet();
        ticket.setId(id);
        tickets.put(id, ticket);
        log.info("Created ticket: {}"+ ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable long id) {
        Ticket ticket = tickets.get(id);
        if (ticket == null) {
            log.info("Ticket not found for ID: {}"+id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Retrieved ticket: {}"+ ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> allTickets = new ArrayList<>(tickets.values());
        log.info("Retrieved all tickets: {}"+ allTickets.size());
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = tickets.get(id);
        if (ticket == null) {
            log.info("Unable to update. Ticket not found for ID: {}"+ id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setDescription(updatedTicket.getDescription());
        ticket.setStatus(updatedTicket.getStatus());
        log.info("Updated ticket: {}"+ ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable long id) {
        Ticket ticket = tickets.remove(id);
        if (ticket == null) {
            log.info("Unable to delete. Ticket not found for ID: {}"+ id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Deleted ticket with ID: {}"+ id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
