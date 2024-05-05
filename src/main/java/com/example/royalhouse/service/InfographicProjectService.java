package com.example.royalhouse.service;

import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.model.InfographicsDTO;

import java.util.List;

public interface InfographicProjectService {
    List<InfographicsProjects> getAll();
    void save(List<InfographicsProjects> infographicsProjects, List<InfographicsDTO> infoMain, List<InfographicsDTO> infoInfrastructure, List<InfographicsDTO> infoApartment);
    void deleteById(Long id);
}
