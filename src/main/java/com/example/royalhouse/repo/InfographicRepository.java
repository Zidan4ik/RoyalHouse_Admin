package com.example.royalhouse.repo;

import com.example.royalhouse.entity.InfographicsProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfographicRepository extends JpaRepository<InfographicsProjects,Long> {
}
