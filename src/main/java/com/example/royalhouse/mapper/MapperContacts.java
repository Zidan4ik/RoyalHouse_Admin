package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Contact;
import com.example.royalhouse.entity.EmailRequest;
import com.example.royalhouse.entity.unifier.ContactsUnifier;
import com.example.royalhouse.model.ContactsDTOEdit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperContacts {
    public static ContactsDTOEdit toDTOEdit(Contact contact, List<EmailRequest> emails){
        ContactsDTOEdit dto = new ContactsDTOEdit();
        dto.setId(contact.getId());
        dto.setPhone(contact.getPhone());
        dto.setViber(contact.getViber());
        dto.setTelegram(contact.getTelegram());
        dto.setEmail(contact.getEmail());
        dto.setInstagram(contact.getInstagram());
        dto.setFacebook(contact.getFacebook());
        dto.setAddress(contact.getAddress());
        dto.setPassword(contact.getPassword());

        dto.setEmails(emails);
        return dto;
    }
    public static ContactsUnifier toEntityEdit(ContactsDTOEdit dto){
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setPhone(dto.getPhone());
        contact.setViber(dto.getViber());
        contact.setTelegram(dto.getTelegram());
        contact.setEmail(dto.getEmail());
        contact.setInstagram(dto.getInstagram());
        contact.setFacebook(dto.getFacebook());
        contact.setAddress(dto.getAddress());
        contact.setPassword(dto.getPassword());

        return new ContactsUnifier(contact,dto.getEmails());
    }
}
