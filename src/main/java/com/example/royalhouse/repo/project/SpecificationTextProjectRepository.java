package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.SpecificationTextProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationTextProjectRepository extends JpaRepository<SpecificationTextProject,Long> {
    List<SpecificationTextProject> getAllByProject(Project project);
}
