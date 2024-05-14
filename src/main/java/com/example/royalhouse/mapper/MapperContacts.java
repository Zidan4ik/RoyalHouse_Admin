package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Contact;
import com.example.royalhouse.entity.EmailRequest;
import com.example.royalhouse.entity.unifier.ContactsUnifier;
import com.example.royalhouse.model.ContactsDTOEdit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperContacts {
    public static ContactsDTOEdit toDTOEdit(Contact contact, List<EmailRequest> emails) {
        ContactsDTOEdit dto = new ContactsDTOEdit();
        dto.setId(contact.getId());
        dto.setPhone(contact.getPhone());
        dto.setViber(contact.getViber());
        dto.setTelegram(contact.getTelegram());
        dto.setEmail(contact.getEmail());
        dto.setInstagram(contact.getInstagram());
        dto.setFacebook(contact.getFacebook());
        dto.setAddress(contact.getAddress());
        if (contact.getLength()!=null) {
            dto.setLength(String.valueOf(contact.getLength()));
        }
        if (contact.getWidth()!=null) {
            dto.setWidth(String.valueOf(contact.getWidth()));
        }
        dto.setPassword(contact.getPassword());
        dto.setEmails(emails);
        return dto;
    }

    public static ContactsUnifier toEntityEdit(ContactsDTOEdit dto) {
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setPhone(dto.getPhone());
        contact.setViber(dto.getViber());
        contact.setTelegram(dto.getTelegram());
        contact.setEmail(dto.getEmail());
        contact.setInstagram(dto.getInstagram());
        contact.setFacebook(dto.getFacebook());
        contact.setAddress(dto.getAddress());
        if (!dto.getLength().isEmpty()) {
            contact.setLength(Double.parseDouble(dto.getLength()));
        } else {
            contact.setLength(null);
        }
        if (!dto.getWidth().isEmpty()) {
            contact.setWidth(Double.parseDouble(dto.getWidth()));
        } else {
            contact.setWidth(null);
        }
        contact.setPassword(dto.getPassword());

        return new ContactsUnifier(contact, dto.getEmails());
    }
}
