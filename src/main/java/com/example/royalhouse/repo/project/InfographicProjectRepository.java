package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.InfographicsProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfographicProjectRepository extends JpaRepository<InfographicsProjects,Long> {
}
