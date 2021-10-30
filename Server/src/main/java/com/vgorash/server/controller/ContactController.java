package com.vgorash.server.controller;

import com.vgorash.server.model.Contact;
import com.vgorash.server.model.SearchType;
import com.vgorash.server.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping(value="/api/contacts", produces = "application/json")
    public List<Contact> getContacts(@RequestParam(name = "search_type", required = false)SearchType searchType,
                                     @RequestParam(name = "query", required = false) String query,
                                     @RequestParam(name = "user_id") String userId){
        return contactService.getContacts(searchType, query, userId);
    }

    @PostMapping(value = "/api/contacts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        contact.setId(null);
        if(contact.getPhone() == null || contact.getUserId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(contactService.addContact(contact));
    }
}
