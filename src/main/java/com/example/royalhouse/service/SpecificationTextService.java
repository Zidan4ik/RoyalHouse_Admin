package com.example.royalhouse.service;

import com.example.royalhouse.entity.SpecificationTextProject;

import java.util.List;

public interface SpecificationTextService {
    List<SpecificationTextProject> getAll();

    void save(List<SpecificationTextProject> specificationTextProject);

    void deleteById(Long id);
}
