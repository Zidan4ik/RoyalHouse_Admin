package com.example.royalhouse.service;

import com.example.royalhouse.entity.EmailRequest;

import java.util.List;

public interface EmailRequestService {
    List<EmailRequest> getAll();
    List<EmailRequest> saveAll(List<EmailRequest> emails);
}
