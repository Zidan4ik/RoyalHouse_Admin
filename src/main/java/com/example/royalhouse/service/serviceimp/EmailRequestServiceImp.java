package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.EmailRequest;
import com.example.royalhouse.repo.EmailRequestRepository;
import com.example.royalhouse.service.EmailRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailRequestServiceImp implements EmailRequestService {
    private final EmailRequestRepository emailRequestRepository;
    @Override
    public List<EmailRequest> getAll() {
        return emailRequestRepository.findAll();
    }

    @Override
    public List<EmailRequest> saveAll(List<EmailRequest> emails) {
        for (int i = 0; i < emails.size(); i++) {
            emails.get(i).setId(i+1l);
        }
        return emailRequestRepository.saveAll(emails);
    }
}
