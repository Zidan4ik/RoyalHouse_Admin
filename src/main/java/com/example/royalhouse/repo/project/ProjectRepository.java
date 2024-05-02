package com.example.royalhouse.repo.project;

import com.example.royalhouse.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
    List<Project> getAllByIndexNum(Integer index);
}
