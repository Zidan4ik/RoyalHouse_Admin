package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.enums.SectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
    Optional<Section> getByType(SectionType type);
}
