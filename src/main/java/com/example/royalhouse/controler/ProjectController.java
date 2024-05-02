package com.example.royalhouse.controler;

import com.example.royalhouse.entity.*;
import com.example.royalhouse.entity.unifier.ProjectUnifier;
import com.example.royalhouse.mapper.MapperProject;
import com.example.royalhouse.model.ProjectDTOAdd;
import com.example.royalhouse.service.serviceimp.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImp projectService;
    private final ImageProjectServiceImp imageProjectService;
    private final InfographicProjectServiceServiceImp infographicProjectServiceService;
    private final TextProjectServiceImp textProjectService;
    private final SpecificationTextProjectServiceImp specificationTextProjectService;

    @GetMapping("")
    public ModelAndView viewProjects(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(required = false) Boolean isActive) {
        ModelAndView model = new ModelAndView("projects/projects-view");
        Pageable pageable = PageRequest.of(page, size, Sort.by("indexNum").ascending());
        Page<Project> pageProjects = projectService.getAll(name, address, isActive, pageable);

        model.addObject("pageProjects", pageProjects);
        model.addObject("projects", pageProjects.getContent());
        model.addObject("currentPage", page);

        model.addObject("name", name);
        model.addObject("address", address);
        model.addObject("isActive", isActive);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView addProjectGM(@ModelAttribute("project") ProjectDTOAdd project) {
        ModelAndView model = new ModelAndView("projects/project-add");
        return model;
    }

    @PostMapping("/add")
    public ModelAndView addProjectPM(
            @ModelAttribute("project") ProjectDTOAdd projectDTO) {

        ModelAndView model = new ModelAndView();
        ProjectUnifier unifier = MapperProject.toEntityAdd(projectDTO);

        Project project = unifier.getProject();
        List<ImagesProject> images = unifier.getImagesProjectList();
        List<InfographicsProjects> infographics = unifier.getInfographicsProjectsList();
        List<TextProject> texts = unifier.getTextProjectList();
        List<SpecificationTextProject> specificationTexts = unifier.getSpecificationTextsList();

        List<Project> projectByIndexNum = projectService.getAllByIndexNum(project.getIndexNum());
        if (projectByIndexNum.size() > 0 && project.getIndexNum() != null) {
            model.addObject("errorIndexNum", "Під цим індексом користувач вже існує");
            model.setViewName("projects/project-add");
            return model;
        }

        projectService.save(project, projectDTO.getBannerMF(), projectDTO.getPanorama());
        imageProjectService.save(images, projectDTO.getImagesAbout(), projectDTO.getImagesInfrastructure(), projectDTO.getImagesApartment());
        textProjectService.save(texts);
        specificationTextProjectService.save(specificationTexts);
        infographicProjectServiceService.save(infographics, projectDTO.getInfographicsMain(), projectDTO.getInfographicsInfrastructure(), projectDTO.getInfographicsApartment());
        model.setViewName("redirect:/projects");
        return model;
    }
}