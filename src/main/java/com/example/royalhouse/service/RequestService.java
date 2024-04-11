package com.example.royalhouse.service;

import com.example.royalhouse.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

public interface RequestService {
    void save(Request request);

    Page<Request> getAll(String fullName, String phone, String email, Boolean isReported, Pageable pageable);

    Optional<Request> getById(Long id);

    void deleteById(Long id);

    void update(Request object);
}
