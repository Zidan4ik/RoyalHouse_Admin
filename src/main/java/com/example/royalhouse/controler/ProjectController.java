package com.example.royalhouse.controler;

import com.example.royalhouse.entity.*;
import com.example.royalhouse.entity.unifier.ProjectUnifier;
import com.example.royalhouse.mapper.MapperProject;
import com.example.royalhouse.model.BindingProjectDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImp projectService;
    private final ImageProjectServiceImp imageProjectService;
    private final InfographicProjectServiceServiceImp infographicProjectServiceService;
    private final TextProjectServiceImp textProjectService;
    private final SpecificationTextProjectServiceImp specificationTextProjectService;
    private final ObjectServiceImp objectService;
    private final RequestServiceImp requestService;

    @GetMapping("")
    public ModelAndView viewProjects(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String address,
                                     @RequestParam(required = false) Boolean isActive) {
        ModelAndView model = new ModelAndView("projects/projects-view");
        Pageable pageable = PageRequest.of(page, size, Sort.by("indexNum").ascending());
        Page<Project> pageProjects = projectService.getAllByFilter(name, address, isActive, pageable);


        model.addObject("pageProjects", pageProjects);
        model.addObject("projects", projectService.getAllFilterIndexNum(pageProjects));
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

        projectService.save(project, projectDTO.getBannerMF(), projectDTO.getPanoramaMF());
        imageProjectService.save(images, projectDTO.getImagesAbout(), projectDTO.getImagesInfrastructure(), projectDTO.getImagesApartment());
        textProjectService.save(texts);
        specificationTextProjectService.save(specificationTexts);
        infographicProjectServiceService.save(infographics, projectDTO.getInfographicsMain(), projectDTO.getInfographicsInfrastructure(), projectDTO.getInfographicsApartment());
        model.setViewName("redirect:/projects");
        return model;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateProjectGM(@PathVariable(name = "id") Long id) {
        ModelAndView model = new ModelAndView("projects/project-update");

        Optional<Project> projectById = projectService.getById(id);
        List<InfographicsProjects> infographics = infographicProjectServiceService.getAllByProject(projectById.get());
        List<ImagesProject> images = imageProjectService.getAllByProject(projectById.get());
        List<SpecificationTextProject> specificationText = specificationTextProjectService.getAllByProject(projectById.get());
        List<TextProject> texts = textProjectService.getAllByProject(projectById.get());

        ProjectUnifier unifier = new ProjectUnifier(projectById.get(), infographics, images, texts, specificationText);
        ProjectDTOAdd dtoAdd = MapperProject.toDTOAdd(unifier);

        model.addObject("project", dtoAdd);
        return model;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateProjectPM(@ModelAttribute(name = "project") ProjectDTOAdd projectDTO) {
        ModelAndView model = new ModelAndView();
        ProjectUnifier unifier = MapperProject.toEntityAdd(projectDTO);
        List<Project> projectByIndexNum = projectService.getAllByIndexNum(projectDTO.getIndexNum());
        if (projectByIndexNum.size() > 0 && projectDTO.getIndexNum() != null) {

            Optional<Project> beforeProject = projectService.getById(projectDTO.getId());

            ProjectUnifier unifierBD = new ProjectUnifier(beforeProject.get(),
                    infographicProjectServiceService.getAllByProject(beforeProject.get()),
                    imageProjectService.getAllByProject(beforeProject.get()),
                    textProjectService.getAllByProject(beforeProject.get()),
                    specificationTextProjectService.getAllByProject(beforeProject.get())
            );
            if (projectDTO.getIndexNum() != beforeProject.get().getIndexNum()) {
                model.addObject("errorIndexNum", "Під цим індексом користувач вже існує");
                model.addObject("project", MapperProject.toDTOAdd(unifierBD));
                model.setViewName("projects/project-update");
                return model;
            }
        }

        Project project = unifier.getProject();
        List<InfographicsProjects> infographics = unifier.getInfographicsProjectsList();
        List<ImagesProject> images = unifier.getImagesProjectList();
        List<TextProject> texts = unifier.getTextProjectList();
        List<SpecificationTextProject> specificationTexts = unifier.getSpecificationTextsList();

        projectService.save(project, projectDTO.getBannerMF(), projectDTO.getPanoramaMF());
        imageProjectService.save(images, projectDTO.getImagesAbout(), projectDTO.getImagesInfrastructure(), projectDTO.getImagesApartment());
        textProjectService.save(texts);
        specificationTextProjectService.save(specificationTexts);
        infographicProjectServiceService.save(infographics, projectDTO.getInfographicsMain(), projectDTO.getInfographicsInfrastructure(), projectDTO.getInfographicsApartment());

        model.setViewName("redirect:/projects");
        return model;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProject(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("redirect:/projects");
        Optional<Project> projectBD = projectService.getById(id);

        List<ImagesProject> images = imageProjectService.getAllByProject(projectBD.get());
        List<InfographicsProjects> infographics = infographicProjectServiceService.getAllByProject(projectBD.get());
        List<TextProject> texts = textProjectService.getAllByProject(projectBD.get());
        List<SpecificationTextProject> specificationTexts = specificationTextProjectService.getAllByProject(projectBD.get());

        for (ImagesProject image : images) {
            imageProjectService.deleteById(image.getId());
        }
        for (InfographicsProjects infographic : infographics) {
            infographicProjectServiceService.deleteById(infographic.getId());
        }
        for (TextProject text : texts) {
            textProjectService.deleteById(text.getId());
        }
        for (SpecificationTextProject text : specificationTexts) {
            specificationTextProjectService.deleteById(text.getId());
        }
        projectService.deleteById(projectBD.get().getId());
        return model;
    }

    @GetMapping("/binding")
    public ModelAndView bindingProjectsGM() {
        ModelAndView model = new ModelAndView("projects/binding-project");
        BindingProjectDTO dto = MapperProject.toDTOFromList(projectService.getAllBlock(), projectService.getAll());
        model.addObject("binding", dto);
        return model;
    }

    @PostMapping("/binding")
    public ModelAndView bindingProjectPM(@ModelAttribute(name = "binding") BindingProjectDTO binding) {
        ModelAndView model = new ModelAndView();
        List<Project> projects = MapperProject.toEntityList(binding);
        boolean flag = projectService.existCopyValues(projects);

        if (flag) {
            BindingProjectDTO dto = MapperProject.toDTOFromList(projectService.getAllBlock(), projectService.getAll());
            model.addObject("binding", dto);
            model.addObject("error", "В одному блоці може бути лише одна новобудівля");
            model.setViewName("projects/binding-project");
            return model;
        }

        projectService.saveBinding(projects);
        model.setViewName("redirect:/requests");
        return model;
    }

    @ModelAttribute("countObjects")
    public int showCountObjects() {
        return objectService.getCountObjects();
    }

    @ModelAttribute("countRequests")
    public int showCountRequest() {
        return requestService.getRequestsByReportedFalse().size();
    }
}