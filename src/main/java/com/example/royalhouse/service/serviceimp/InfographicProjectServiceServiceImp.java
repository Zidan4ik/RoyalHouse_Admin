package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.entity.Project;
import com.example.royalhouse.enums.InfographicsType;
import com.example.royalhouse.model.InfographicsDTO;
import com.example.royalhouse.repo.project.InfographicProjectRepository;
import com.example.royalhouse.service.InfographicProjectService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;

        for (int i = 0; i < infographicsProjects.size(); i++) {
            List<InfographicsProjects> infographicsProjectsBD = infographicProjectRepository.getAllByProject(infographicsProjects.get(i).getProject());
            if(!infographicsProjectsBD.isEmpty()){
                infographicsProjects.get(i).setId(infographicsProjectsBD.get(i).getId());
                infographicsProjects.get(i).setImage(infographicsProjectsBD.get(i).getImage());
            }
        }

        try {
            for (InfographicsProjects i : infographicsProjects) {
                if (i.getType() == InfographicsType.main) {
                    InfographicsDTO infographicsDTO = infoMain.get(counter1);
                    if (!infoMain.isEmpty() && !infographicsDTO.getMultipartFile().isEmpty()) {
                        String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                        i.setImage(nameFile);
                    }
                    counter1++;
                }
                if (i.getType() == InfographicsType.infrastructure) {
                    InfographicsDTO infographicsDTO = infoInfrastructure.get(counter2);
                    if (!infoInfrastructure.isEmpty() && !infographicsDTO.getMultipartFile().isEmpty()) {
                        String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                        i.setImage(nameFile);
                    }
                    counter2++;
                }
                if (i.getType() == InfographicsType.apartment) {
                    InfographicsDTO infographicsDTO = infoApartment.get(counter3);
                    if (!infoApartment.isEmpty() && !infographicsDTO.getMultipartFile().isEmpty()) {
                        String nameFile = UUID.randomUUID() + "." + StringUtils.cleanPath(infographicsDTO.getMultipartFile().getOriginalFilename());
                        i.setImage(nameFile);
                    }
                    counter3++;
                }
            }
            for (InfographicsProjects i : infographicsProjects) {
                infographicProjectRepository.save(i);
            }
            counter1 = 0;
            counter2 = 0;
            counter3 = 0;
            for (InfographicsProjects i : infographicsProjects) {
                if (i.getType() == InfographicsType.main) {
                    InfographicsDTO infographicsDTO = infoMain.get(counter1);
                    if (!infographicsDTO.getMultipartFile().isEmpty()) {
                        path = "./uploads/project/infographics/main/" + i.getId();
                        Image.saveFile(path, infographicsDTO.getMultipartFile(), i.getImage());
                    }
                    counter1++;
                }
                if (i.getType() == InfographicsType.infrastructure) {
                    InfographicsDTO infographicsDTO = infoInfrastructure.get(counter2);
                    if (!infographicsDTO.getMultipartFile().isEmpty()) {
                        path = "./uploads/project/infographics/infrastructure/" + i.getId();
                        Image.saveFile(path, infographicsDTO.getMultipartFile(), i.getImage());
                    }
                    counter2++;
                }
                if (i.getType() == InfographicsType.apartment) {
                    InfographicsDTO infographicsDTO = infoApartment.get(counter3);
                    if (!infographicsDTO.getMultipartFile().isEmpty()) {
                        path = "./uploads/project/infographics/apartment/" + i.getId();
                        Image.saveFile(path, infographicsDTO.getMultipartFile(), i.getImage());
                    }
                    counter3++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        infographicProjectRepository.deleteById(id);
    }

    public List<InfographicsProjects> getAllByProject(Project project) {
        return infographicProjectRepository.getAllByProject(project);
    }
}
