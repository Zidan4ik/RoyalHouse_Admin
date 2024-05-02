package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.ImagesProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProjectRepository extends JpaRepository<ImagesProject,Long> {
}
