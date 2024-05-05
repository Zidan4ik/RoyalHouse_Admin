package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.ImagesProject;
import com.example.royalhouse.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProjectRepository extends JpaRepository<ImagesProject,Long> {
    List<ImagesProject> getAllByProject(Project project);
    void deleteAllByProject(Project project);
}
