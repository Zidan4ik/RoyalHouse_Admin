package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Contact;
import com.example.royalhouse.repo.ContactsRepository;
import com.example.royalhouse.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {
    private final ContactsRepository contactsRepository;
    @Override
    public Optional<Contact> getById(Long id) {
        return contactsRepository.findById(id);
    }

    @Override
    public void save(Contact contact) {
     contactsRepository.save(contact);
    }
}
