package com.example.royalhouse.controler;

import com.example.royalhouse.entity.InfographicsProjects;
import com.example.royalhouse.entity.Project;
import com.example.royalhouse.service.serviceimp.ProjectServiceImp;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImp projectService;
    @GetMapping("")
    public ModelAndView viewProjects(@RequestParam(defaultValue = "0")int page,
                                     @RequestParam(defaultValue = "3")int size,
                                     @RequestParam(required = false)String name,
                                     @RequestParam(required = false)String address,
                                     @RequestParam(required = false)Boolean isActive) {
        ModelAndView model = new ModelAndView("projects/projects-view");
        Pageable pageable = PageRequest.of(page,size,Sort.by("indexNum").ascending());
        Page<Project> pageProjects = projectService.getAll(name, address, isActive, pageable);

        model.addObject("pageProjects",pageProjects);
        model.addObject("projects",pageProjects.getContent());
        model.addObject("currentPage",page);

        model.addObject("name",name);
        model.addObject("address",address);
        model.addObject("isActive",isActive);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView addProjectGM(@ModelAttribute("project") Project project){
        ModelAndView model = new ModelAndView("projects/project-add");

        model.addObject("infographics",new ArrayList<InfographicsProjects>());
        return model;
    }

    @PostMapping("/add")
    public ModelAndView addProjectPM(@ModelAttribute("project") Project project,
                                     @RequestParam(name = "info")MultipartFile[] multipartFile,
                                     @RequestParam(name = "descriptions")String [] descriptions){
        ModelAndView model = new ModelAndView("projects/project-add");
        for(MultipartFile m:multipartFile){
            if(!m.getOriginalFilename().isEmpty())
            System.out.println(m.getOriginalFilename());
        }
        for (String s:descriptions){
            System.out.println(s);
        }

        return model;
    }
}