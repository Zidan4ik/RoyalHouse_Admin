package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.repo.ProjectsRepository;
import com.example.royalhouse.service.ProjectService;
import com.example.royalhouse.service.specification.ProjectSpecification;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectsRepository projectsRepository;

    @Override
    public Page<Project> getAll(String name, String address, Boolean isActive, Pageable pageable) {

        Specification<Project> filter = Specification.where(
                StringUtils.isBlank(name) ? null : ProjectSpecification.hasName(name)
                        .and(StringUtils.isBlank(address) ? null : ProjectSpecification.hasName(address))
                        .and(isActive == null ? null : ProjectSpecification.isActive(isActive))
        );
        return projectsRepository.findAll(filter,pageable);
    }

    @Override
    public Optional<Project> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
