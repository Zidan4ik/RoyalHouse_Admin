package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.repo.InfographicRepository;
import com.example.royalhouse.service.InfographicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfographicServiceImp implements InfographicService {
    private final InfographicRepository infographicRepository;
    @Override
    public List<InfographicsProjects> getAll() {
        return infographicRepository.findAll();
    }
}
