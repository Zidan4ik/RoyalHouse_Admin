package com.example.royalhouse.service;

import com.example.royalhouse.entity.Object;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ObjectService {
    void save(Object object, MultipartFile[] multipartFiles);
    List<Object> getAll();
    Page<Object> getAllPagination(Pageable pageable);
    Optional<Object> getById(Long id);
    void deleteById(Long id);
    void update(Object object,MultipartFile[] multipartFiles);
}
