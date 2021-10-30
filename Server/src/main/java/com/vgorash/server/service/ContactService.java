package com.vgorash.server.service;

import com.vgorash.server.model.*;
import com.vgorash.server.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(SearchType searchType, String query, String userId){
        if(searchType == null || query == null){
            return contactRepository.findAllByUserId(userId);
        }
        String likeQuery = "%"+query+"%";
        switch (searchType){
            case NAME:
                return contactRepository.getByName(likeQuery, userId);
            case SURNAME:
                return contactRepository.getBySurname(likeQuery, userId);
            case FULLNAME:
                return contactRepository.getByFullName(likeQuery, userId);
            case EMAIL:
                return contactRepository.getByEmail(likeQuery, userId);
            case PHONE:
                return contactRepository.getByPhone(likeQuery, userId);
            case ALL:
                return contactRepository.getBySubstring(likeQuery, userId);
        }
        return null;
    }

    public Contact addContact(Contact contact){
        return contactRepository.saveAndFlush(contact);
    }

}
