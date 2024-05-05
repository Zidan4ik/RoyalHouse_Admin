package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.TextProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextProjectRepository extends JpaRepository<TextProject,Long> {
    List<TextProject> getAllByProject(Project project);
}
