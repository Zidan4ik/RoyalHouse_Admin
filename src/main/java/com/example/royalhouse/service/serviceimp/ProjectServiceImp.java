package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.repo.project.ProjectRepository;
import com.example.royalhouse.service.ProjectService;
import com.example.royalhouse.service.specification.ProjectSpecification;
import com.example.royalhouse.util.Image;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public void save(Project project, MultipartFile banner, MultipartFile panorama) {
        String uploadDir;
        String bannerName = null;
        String panoramaName = null;

        if(!banner.isEmpty()){
            bannerName = UUID.randomUUID()+"."+ StringUtils.cleanPath(banner.getOriginalFilename());
            project.setBanner(bannerName);
        }
        if(!panorama.isEmpty()){
            panoramaName = UUID.randomUUID()+"."+ StringUtils.cleanPath(panorama.getOriginalFilename());
            project.setBanner(panoramaName);
        }
        projectRepository.save(project);

        try {
            if(!banner.getOriginalFilename().isEmpty()){
                uploadDir = "./uploads/project/banner/"+project.getId();
                Image.saveFile(uploadDir,banner,bannerName);
            }
            if(!panorama.getOriginalFilename().isEmpty()){
                uploadDir = "./uploads/project/panorama/"+project.getId();
                Image.saveFile(uploadDir,panorama,panoramaName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Project> getAll(String name, String address, Boolean isActive, Pageable pageable) {

        Specification<Project> filter = Specification.where(
                io.micrometer.common.util.StringUtils.isBlank(name) ? null : ProjectSpecification.hasName(name)
                        .and(io.micrometer.common.util.StringUtils.isBlank(address) ? null : ProjectSpecification.hasName(address))
                        .and(isActive == null ? null : ProjectSpecification.isActive(isActive))
        );
        return projectRepository.findAll(filter, pageable);
    }

    @Override
    public Optional<Project> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void update(Project project, MultipartFile banner, MultipartFile panorama) {
        projectRepository.save(project);
    }

    public List<Project> getAllByIndexNum(Integer index) {
        return projectRepository.getAllByIndexNum(index);
    }
}
