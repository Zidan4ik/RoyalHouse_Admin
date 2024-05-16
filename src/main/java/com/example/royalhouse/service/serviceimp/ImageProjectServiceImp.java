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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        List<String> namesDeleteAboutProject = new ArrayList<>();
        List<String> namesDeleteInfrastructure = new ArrayList<>();
        List<String> namesDeleteApartment = new ArrayList<>();

        for (ImagesProject images : imagesProject) {
            List<ImagesProject> imagesByProject = imageProjectRepository.getAllByProject(images.getProject());
            for (ImagesProject i : imagesByProject) {
                if (i.getType().equals(images.getType())) {
                    images.setId(i.getId());
                    images.setImageFirst(i.getImageFirst());
                    images.setImageSecond(i.getImageSecond());
                    images.setImageThird(i.getImageThird());
                    break;
                }
            }
        }

        for (ImagesProject images : imagesProject) {
            if (images.getType() == ImageType.aboutProject) {
                namesDeleteAboutProject = divideImages(images, nameAbout, multipartFilesAbout);
            }
            if (images.getType() == ImageType.infrastructure) {
                namesDeleteInfrastructure = divideImages(images, nameInfrastructure, multipartFilesInfrastructure);
            }
            if (images.getType() == ImageType.apartment) {
                namesDeleteApartment = divideImages(images, nameApartment, multipartFilesApartment);
            }
        }

        for (ImagesProject i : imagesProject) {
            imageProjectRepository.save(i);
        }

        for (ImagesProject i : imagesProject) {
            if (i.getType() == ImageType.aboutProject) {
                String uploadDir = "./uploads/project/images/about/" + i.getId();
                if (!namesDeleteAboutProject.isEmpty()) {
                    Image.deleteFiles(uploadDir, namesDeleteAboutProject);
                }
                saveImages(uploadDir, multipartFilesAbout, new String[]{i.getImageFirst(), i.getImageSecond(), i.getImageThird()});
            }
            if (i.getType() == ImageType.infrastructure) {
                String uploadDir = "./uploads/project/images/infrastructure/" + i.getId();
                if (!namesDeleteInfrastructure.isEmpty()) {
                    Image.deleteFiles(uploadDir, namesDeleteInfrastructure);
                }
                saveImages(uploadDir, multipartFilesInfrastructure, new String[]{i.getImageFirst(), i.getImageSecond(), i.getImageThird()});
            }
            if (i.getType() == ImageType.apartment) {
                String uploadDir = "./uploads/project/images/apartment/" + i.getId();
                if (!namesDeleteApartment.isEmpty()) {
                    Image.deleteFiles(uploadDir, namesDeleteApartment);
                }
                saveImages(uploadDir, multipartFilesApartment, new String[]{i.getImageFirst(), i.getImageSecond(), i.getImageThird()});
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<ImagesProject> byId = imageProjectRepository.findById(id);
        imageProjectRepository.deleteById(id);
        if(byId.get().getType().equals(ImageType.aboutProject)){
            Image.fullDelete("./uploads/project/images/about/" + id);
        }
        if(byId.get().getType().equals(ImageType.infrastructure)){
            Image.fullDelete("./uploads/project/images/infrastructure/" + id);
        }
        if(byId.get().getType().equals(ImageType.apartment)){
            Image.fullDelete("./uploads/project/images/apartment/" + id);
        }
    }

    public List<ImagesProject> getAllByProject(Project project) {
        return imageProjectRepository.getAllByProject(project);
    }

    public void deleteAllByProject(Project project) {
        imageProjectRepository.deleteAllByProject(project);
    }

    private void saveImages(String path, MultipartFile[] multipartFile, String[] nameFile) {
        try {
            for (int i = 0; i <= 2; i++) {
                if (!multipartFile[i].getOriginalFilename().isEmpty()) {
                    Image.saveFile(path, multipartFile[i], nameFile[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<String> divideImages(ImagesProject imagesProject, String[] nameOfSection, MultipartFile[] multipartFiles) {
        List<String> nameDelete = new ArrayList<>();
        if (!multipartFiles[0].isEmpty()) {
            nameDelete.add(imagesProject.getImageFirst());
            nameOfSection[0] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[0].getOriginalFilename());
            imagesProject.setImageFirst(nameOfSection[0]);
        }
        if (!multipartFiles[1].isEmpty()) {
            nameDelete.add(imagesProject.getImageSecond());
            nameOfSection[1] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[1].getOriginalFilename());
            imagesProject.setImageSecond(nameOfSection[1]);
        }
        if (!multipartFiles[2].isEmpty()) {
            nameDelete.add(imagesProject.getImageThird());
            nameOfSection[2] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[2].getOriginalFilename());
            imagesProject.setImageThird(nameOfSection[2]);
        }
        return nameDelete;
    }

}
