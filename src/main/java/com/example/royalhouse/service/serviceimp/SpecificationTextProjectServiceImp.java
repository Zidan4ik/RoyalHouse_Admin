package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.SpecificationTextProject;
import com.example.royalhouse.repo.project.SpecificationTextProjectRepository;
import com.example.royalhouse.service.SpecificationTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificationTextProjectServiceImp implements SpecificationTextService {
    private final SpecificationTextProjectRepository specificationTextProjectRepository;

    @Override
    public List<SpecificationTextProject> getAll() {
        return specificationTextProjectRepository.findAll();
    }

    @Override
    public void save(List<SpecificationTextProject> specificationTextProject) {
        for (int i = 0; i < specificationTextProject.size(); i++) {
            if(specificationTextProject.get(i).getProject()!=null){
                List<SpecificationTextProject> specificationText = specificationTextProjectRepository.getAllByProject(specificationTextProject.get(i).getProject());
                if(!specificationText.isEmpty()) {
                    specificationTextProject.get(i).setId(specificationText.get(i).getId());
                }
            }
        }
        for (SpecificationTextProject text : specificationTextProject) {
            specificationTextProjectRepository.save(text);
        }
    }

    @Override
    public void deleteById(Long id) {
        specificationTextProjectRepository.deleteById(id);
    }
    public List<SpecificationTextProject> getAllByProject(Project project){
        return specificationTextProjectRepository.getAllByProject(project);
    }
}
