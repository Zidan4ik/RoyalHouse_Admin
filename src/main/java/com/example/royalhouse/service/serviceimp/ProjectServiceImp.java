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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;
    @Override
    public void save(Project project, MultipartFile banner, MultipartFile panorama) {
        String uploadDir;
        String bannerName = null;
        String panoramaName = null;
        List<String> bannerDelete = new ArrayList<>();
        List<String> panoramaDelete = new ArrayList<>();
        Optional<Project> projectById = null;

        if (project.getId() != null) {
            projectById = projectRepository.findById(project.getId());
            project.setBanner(projectById.get().getBanner());
            project.setImagePanorama(projectById.get().getImagePanorama());
        }
        if (!banner.isEmpty()) {
            if(project.getId()!=null){
                bannerDelete.add(projectById.get().getBanner());
            }
            bannerName = UUID.randomUUID() + "." + StringUtils.cleanPath(banner.getOriginalFilename());
            project.setBanner(bannerName);
        }
        if (!panorama.isEmpty()) {
            if(project.getId()!=null){
                panoramaDelete.add(projectById.get().getImagePanorama());
            }
            panoramaName = UUID.randomUUID() + "." + StringUtils.cleanPath(panorama.getOriginalFilename());
            project.setImagePanorama(panoramaName);
        }
        projectRepository.save(project);

        try {
            if (!banner.getOriginalFilename().isEmpty()) {
                uploadDir = "./uploads/project/banner/" + project.getId();
                if(projectById!=null){
                    Image.deleteFiles(uploadDir,bannerDelete);
                }
                Image.saveFile(uploadDir, banner, bannerName);
            }
            if (!panorama.getOriginalFilename().isEmpty()) {
                uploadDir = "./uploads/project/panorama/" + project.getId();
                if(projectById!=null){
                    Image.deleteFiles(uploadDir,panoramaDelete);
                }
                Image.saveFile(uploadDir, panorama, panoramaName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Project> getAllByFilter(String name, String address, Boolean isActive, Pageable pageable) {

        Specification<Project> filter = Specification.where(
                        io.micrometer.common.util.StringUtils.isBlank(name) ? null : ProjectSpecification.hasName(name))
                .and(io.micrometer.common.util.StringUtils.isBlank(address) ? null : ProjectSpecification.hasAddress(address))
                .and(isActive == null ? null : ProjectSpecification.isActive(isActive));
        return projectRepository.findAll(filter, pageable);
    }

    @Override
    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
        Image.fullDelete("./uploads/project/banner/"+id);
        Image.fullDelete("./uploads/project/panorama/"+id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllByIndexNum(Integer index) {
        return projectRepository.getAllByIndexNum(index);
    }

    public List<Project> getAllFilterIndexNum(Page<Project> page) {
        List<Project> list = new ArrayList<>();
        List<Project> list1 = page.getContent().stream().filter(s -> s.getIndexNum() != null).collect(Collectors.toList());
        List<Project> list2 = page.getContent().stream().filter(s -> s.getIndexNum() == null).collect(Collectors.toList());
        list.addAll(list1);
        list.addAll(list2);
       return list;
    }
    public List<Project> getAllBlock(){
        return projectRepository.getAllByBlockIsNotNull();
    }
    public List<Project> saveBinding(List<Project> project){
        return projectRepository.saveAll(project);
    }
    public boolean existCopyValues(List<Project> projects){
        boolean flag = false;
        for (int i=0;i<projects.size();i++){
            for (int j=i+1;j<projects.size();j++){
                if(projects.get(i).getBlock().equals(projects.get(j).getBlock())){
                    flag=true;
                }
            }
        }
        return flag;
    }
}
