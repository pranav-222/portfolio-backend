package portfolio_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import portfolio_backend.entity.ContactMessage;
import portfolio_backend.service.ContactService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    public ResponseEntity<String> send(
            @RequestBody ContactMessage message) {

        System.out.println("CONTACT REQUEST RECEIVED FROM: " + message.getEmail());

        service.saveMessage(message);

        return ResponseEntity.ok("Message Sent Successfully");
    }
}