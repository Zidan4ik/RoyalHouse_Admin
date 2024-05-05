package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfographicProjectRepository extends JpaRepository<InfographicsProjects,Long> {
    List<InfographicsProjects> getAllByProject(Project project);
}
