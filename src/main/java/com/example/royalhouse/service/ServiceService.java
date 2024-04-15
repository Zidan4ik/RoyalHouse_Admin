package com.example.royalhouse.service;

import com.example.royalhouse.entity.Service_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ServiceService {
    void save(Service_ service, MultipartFile image, MultipartFile banner);
    Optional<Service_> getById(Long id);
    void update(Service_ service, MultipartFile image, MultipartFile banner);
    Page<Service_> getAll(String name, Boolean isReflection, Pageable pageable);
    void deleteById(Long id);
}
