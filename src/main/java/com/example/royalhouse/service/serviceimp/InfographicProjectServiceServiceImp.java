package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.ImagesProject;
import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.enums.InfographicsType;
import com.example.royalhouse.model.InfographicsDTO;
import com.example.royalhouse.repo.project.InfographicProjectRepository;
import com.example.royalhouse.service.InfographicProjectService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InfographicProjectServiceServiceImp implements InfographicProjectService {
    private final InfographicProjectRepository infographicProjectRepository;

    @Override
    public List<InfographicsProjects> getAll() {
        return infographicProjectRepository.findAll();
    }

    @Override
    public void save(List<InfographicsProjects> infographicsProjects, List<InfographicsDTO> infoMain, List<InfographicsDTO> infoInfrastructure, List<InfographicsDTO> infoApartment) {
        String path;
        try {
            for (InfographicsProjects infographics : infographicsProjects) {
                if (infographics.getType() == InfographicsType.main) {
                    for (InfographicsDTO infographicsDTO : infoMain) {
                        if (infographicsDTO.getId().equals(infographics.getId())) {
                            String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                            path = "./uploads/projects/infographics/main/" + infographics.getId();
                            infographics.setPath(nameFile);
                            infographicProjectRepository.save(infographics);
                            Image.saveFile(path, infographicsDTO.getMultipartFile(), nameFile);
                        }
                    }
                }
                if (infographics.getType() == InfographicsType.infrastructure) {
                    for (InfographicsDTO infographicsDTO : infoInfrastructure) {
                        if (infographicsDTO.getId().equals(infographics.getId())) {
                            String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                            path = "./uploads/projects/infographics/infrastructure/" + infographics.getId();
                            infographics.setPath(nameFile);
                            infographicProjectRepository.save(infographics);
                            Image.saveFile(path, infographicsDTO.getMultipartFile(), nameFile);
                        }
                    }
                }
                if (infographics.getType() == InfographicsType.apartment) {
                    for (InfographicsDTO infographicsDTO : infoApartment) {
                        if (infographicsDTO.getId().equals(infographics.getId())) {
                            String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                            path = "./uploads/projects/infographics/apartment/" + infographics.getId();
                            infographics.setPath(nameFile);
                            infographicProjectRepository.save(infographics);
                            Image.saveFile(path, infographicsDTO.getMultipartFile(), nameFile);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(List<InfographicsProjects> infographicsProjects) {
        for (InfographicsProjects infographics : infographicsProjects) {
            infographicProjectRepository.save(infographics);
        }
    }

    @Override
    public void deleteById(Long id) {
        infographicProjectRepository.deleteById(id);
    }
//    private void divideImages(InfographicsProjects infographicsProjects, String[] nameOfSection, MultipartFile[] multipartFiles) {
//        if (!multipartFiles[0].isEmpty()) {
//            nameOfSection[0] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[0].getOriginalFilename());
//        }
//        if (!multipartFiles[1].isEmpty()) {
//            nameOfSection[1] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[1].getOriginalFilename());
//        }
//        if (!multipartFiles[2].isEmpty()) {
//            nameOfSection[2] = UUID.randomUUID() + "." + StringUtils.cleanPath(multipartFiles[2].getOriginalFilename());
//        }
//
//        imagesProject.setImageFirst(nameOfSection[0]);
//        imagesProject.setImageSecond(nameOfSection[1]);
//        imagesProject.setImageThird(nameOfSection[2]);
//    }
}
