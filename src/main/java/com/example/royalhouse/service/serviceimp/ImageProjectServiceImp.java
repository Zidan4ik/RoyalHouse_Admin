package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.ImagesProject;
import com.example.royalhouse.entity.Project;
import com.example.royalhouse.enums.ImageType;
import com.example.royalhouse.repo.project.ImageProjectRepository;
import com.example.royalhouse.service.ImageProjectService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageProjectServiceImp implements ImageProjectService {
    private final ImageProjectRepository imageProjectRepository;

    @Override
    public List<ImagesProject> getAll() {
        return imageProjectRepository.findAll();
    }

    @Override
    public void save(List<ImagesProject> imagesProject, MultipartFile[] multipartFilesAbout, MultipartFile[] multipartFilesInfrastructure, MultipartFile[] multipartFilesApartment) {

        String[] nameAbout = new String[3];
        String[] nameInfrastructure = new String[3];
        String[] nameApartment = new String[3];
        for (ImagesProject images : imagesProject) {
            if (images.getType() == ImageType.aboutProject) {
                divideImages(images, nameAbout, multipartFilesAbout);
                imageProjectRepository.save(images);
            }
            if (images.getType() == ImageType.infrastructure) {
                divideImages(images, nameInfrastructure, multipartFilesInfrastructure);
                imageProjectRepository.save(images);
            }
            if (images.getType() == ImageType.apartment) {
                divideImages(images, nameApartment, multipartFilesApartment);
                imageProjectRepository.save(images);
            }
        }

        for (ImagesProject i : imagesProject) {
            if (i.getType() == ImageType.aboutProject) {
                String uploadDir = "./uploads/project/images/about/" + i.getId();
                saveImages(uploadDir,multipartFilesAbout,nameAbout);
            }
            if (i.getType() == ImageType.infrastructure) {
                String uploadDir = "./uploads/project/images/infrastructure/" + i.getId();
                saveImages(uploadDir,multipartFilesInfrastructure,nameInfrastructure);
            }
            if (i.getType() == ImageType.apartment) {
                String uploadDir = "./uploads/project/images/apartment/" + i.getId();
                saveImages(uploadDir,multipartFilesApartment,nameApartment);
            }
        }

    }

    @Override
    public void update(List<ImagesProject> imagesProject, MultipartFile[] multipartFilesAbout, MultipartFile[] multipartFilesInfrastructure, MultipartFile[] multipartFilesApartment) {
//        imageProjectRepository.save(imagesProject);
    }

    @Override
    public void deleteById(Long id) {
        imageProjectRepository.deleteById(id);
    }

    private void saveImages(String path, MultipartFile[] multipartFile, String[] nameFile) {
        try {
            for (int i = 0; i <= 2; i++) {
                if(!multipartFile[i].getOriginalFilename().isEmpty()){
                    Image.saveFile(path, multipartFile[i], nameFile[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void divideImages(ImagesProject imagesProject, String[] nameOfSection, MultipartFile[] multipartFiles) {
        if (!multipartFiles[0].isEmpty()) {
            nameOfSection[0] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[0].getOriginalFilename());
        }
        if (!multipartFiles[1].isEmpty()) {
            nameOfSection[1] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[1].getOriginalFilename());
        }
        if (!multipartFiles[2].isEmpty()) {
            nameOfSection[2] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[2].getOriginalFilename());
        }

        imagesProject.setImageFirst(nameOfSection[0]);
        imagesProject.setImageSecond(nameOfSection[1]);
        imagesProject.setImageThird(nameOfSection[2]);
    }
}
