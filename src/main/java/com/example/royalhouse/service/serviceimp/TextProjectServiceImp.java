package com.example.royalhouse.service.serviceimp;


import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.TextProject;
import com.example.royalhouse.repo.project.TextProjectRepository;
import com.example.royalhouse.service.TextProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextProjectServiceImp implements TextProjectService {
    private final TextProjectRepository textProjectRepository;

    @Override
    public List<TextProject> getAll() {
        return textProjectRepository.findAll();
    }

    @Override
    public void save(List<TextProject> textProject) {
        for (TextProject text:textProject){
            List<TextProject> texts = textProjectRepository.getAllByProject(text.getProject());
            for (TextProject t:texts){
                if(t.getType().equals(text.getType())){
                    text.setId(t.getId());
                    break;
                }
            }
        }
        for (TextProject text : textProject) {
            textProjectRepository.save(text);
        }
    }
    @Override
    public void deleteById(Long id) {
        textProjectRepository.deleteById(id);
    }
    public List<TextProject> getAllByProject(Project project){
        return textProjectRepository.getAllByProject(project);
    }
}
