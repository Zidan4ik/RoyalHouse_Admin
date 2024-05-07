package com.example.royalhouse.service;

import com.example.royalhouse.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void save(Project project, MultipartFile banner, MultipartFile panorama) throws IOException;

    Page<Project> getAllByFilter(String name, String address, Boolean isActive, Pageable pageable);

    Optional<Project> getById(Long id);

    void deleteById(Long id);
    List<Project> getAll();

}
