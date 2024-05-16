package com.example.royalhouse.service;

import com.example.royalhouse.entity.Section;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface SectionService {
    void save(Section section, MultipartFile multipartFile, MultipartFile image1 , MultipartFile image2);
    Optional<Section> getById(Long id);
}
