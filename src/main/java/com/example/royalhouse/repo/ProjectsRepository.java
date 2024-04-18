package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectsRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
}
