package com.example.royalhouse.controler;


import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.repo.ObjectRepository;
import com.example.royalhouse.util.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/objects")
public class ControllerObjects {
    @Autowired
    private ObjectRepository objectRepository;

    @GetMapping("")
    public ModelAndView viewObjects(){
        ModelAndView model = new ModelAndView("objects/objects-view");
        Iterable<Object> objectsFromDB = objectRepository.findAll();
        model.addObject("objects",objectsFromDB);
        return model;
    }
    @GetMapping("/add")
    public ModelAndView addObjectGM(@ModelAttribute("object")Object object){
        ModelAndView model = new ModelAndView("objects/objects-add");

        List<String> buildings = new ArrayList<>();
        buildings.add("house");
        buildings.add("apartment");
        buildings.add("plot");
        buildings.add("commercial");

        model.addObject("buildings",buildings);
        return model;
    }
    @PostMapping()
    public String addObjectPM(@ModelAttribute("object")Object object,
                              @RequestParam("building") String building,
                              @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {
        if(building.equals(Building.house)){
            object.setBuilding(Building.house);
        }else if(building.equals(Building.plot)){
            object.setBuilding(Building.plot);
        }else if(building.equals(Building.apartment)){
            object.setBuilding(Building.apartment);
        }else if(building.equals(Building.commercial)){
            object.setBuilding(Building.commercial);
        }


        object.setImageFirst(Image.getPathToFileImage(multipartFile));

        object.setDateOfAddition(LocalDateTime.now());
        objectRepository.save(object);
        return "redirect:/objects";
    }
}