package com.example.royalhouse.service;

import com.example.royalhouse.entity.ImagesProject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageProjectService {
    List<ImagesProject> getAll();

    void save(List<ImagesProject> imagesProject,  MultipartFile[] multipartFilesAbout,MultipartFile[] multipartFilesInfrastructure,MultipartFile[] multipartFilesApartment);

    void deleteById(Long id);
}
