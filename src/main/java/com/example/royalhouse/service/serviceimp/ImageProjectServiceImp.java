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

        for (ImagesProject images: imagesProject){
            List<ImagesProject> imagesByProject = imageProjectRepository.getAllByProject(images.getProject());
            for (ImagesProject i:imagesByProject){
                if(i.getType().equals(images.getType())){
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
                divideImages(images, nameAbout, multipartFilesAbout);
            }
            if (images.getType() == ImageType.infrastructure) {
                divideImages(images, nameInfrastructure, multipartFilesInfrastructure);
            }
            if (images.getType() == ImageType.apartment) {
                divideImages(images, nameApartment, multipartFilesApartment);
            }
        }

        for (ImagesProject i:imagesProject){
            imageProjectRepository.save(i);
        }

        for (ImagesProject i : imagesProject) {
            if (i.getType() == ImageType.aboutProject) {
                String uploadDir = "./uploads/project/images/about/" + i.getId();
                saveImages(uploadDir,multipartFilesAbout,new String[]{i.getImageFirst(),i.getImageSecond(),i.getImageThird()});
            }
            if (i.getType() == ImageType.infrastructure) {
                String uploadDir = "./uploads/project/images/infrastructure/" + i.getId();
                saveImages(uploadDir,multipartFilesInfrastructure,new String[]{i.getImageFirst(),i.getImageSecond(),i.getImageThird()});
            }
            if (i.getType() == ImageType.apartment) {
                String uploadDir = "./uploads/project/images/apartment/" + i.getId();
                saveImages(uploadDir,multipartFilesApartment,new String[]{i.getImageFirst(),i.getImageSecond(),i.getImageThird()});
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        imageProjectRepository.deleteById(id);
    }

    public List<ImagesProject> getAllByProject(Project project){
        return imageProjectRepository.getAllByProject(project);
    }
    public void deleteAllByProject(Project project){
        imageProjectRepository.deleteAllByProject(project);
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
            imagesProject.setImageFirst(nameOfSection[0]);
        }
        if (!multipartFiles[1].isEmpty()) {
            nameOfSection[1] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[1].getOriginalFilename());
            imagesProject.setImageSecond(nameOfSection[1]);
        }
        if (!multipartFiles[2].isEmpty()) {
            nameOfSection[2] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[2].getOriginalFilename());
            imagesProject.setImageThird(nameOfSection[2]);
        }
    }

}
