package com.example.royalhouse.entity.unifier;

import com.example.royalhouse.entity.Contact;
import com.example.royalhouse.entity.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContactsUnifier {
    private Contact contact;
    private List<EmailRequest> emails;
}
