package com.example.royalhouse.service;

import com.example.royalhouse.entity.TextProject;

import java.util.List;

public interface TextProjectService {
    List<TextProject> getAll();

    void save(List<TextProject> textProject);

    void deleteById(Long id);
}
