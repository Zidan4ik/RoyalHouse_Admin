package com.example.royalhouse.service;


import com.example.royalhouse.entity.Contact;

import java.util.Optional;

public interface ContactService {
    Optional<Contact> getById(Long id);
    void save(Contact contact);
}
