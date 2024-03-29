package com.example.royalhouse.controler;


import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.mapper.TransferObject;
import com.example.royalhouse.model.ObjectDTOAdd;
import com.example.royalhouse.model.ObjectDTOView;
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
    @Autowired
    private TransferObject transferObject;
    @GetMapping("")
    public ModelAndView viewObjects(){
        ModelAndView model = new ModelAndView("objects/objects-view");
        Iterable<Object> objectsFromDB = objectRepository.findAll();
        model.addObject("objects",objectsFromDB);
        return model;
    }
    @GetMapping("/add")
    public ModelAndView addObjectGM(@ModelAttribute("object") ObjectDTOAdd objectDTOAdd){
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
    public String addObjectPM(@ModelAttribute("object")ObjectDTOAdd objectDTOAdd,
                              @RequestParam("building") String building,
                              @RequestParam("extraImage")MultipartFile[] multipartFiles) throws IOException {
        Object objectEntity = transferObject.toTransferEntityAdd(objectDTOAdd);
        if(building.equals(Building.house)){
            objectEntity.setBuilding(Building.house);
        }else if(building.equals(Building.plot)){
            objectEntity.setBuilding(Building.plot);
        }else if(building.equals(Building.apartment)){
            objectEntity.setBuilding(Building.apartment);
        }else if(building.equals(Building.commercial)){
            objectEntity.setBuilding(Building.commercial);
        }

        int counter = 0;
        for (MultipartFile file: multipartFiles){
            String imageName = StringUtils.cleanPath(file.getOriginalFilename());

            if(counter==0)objectEntity.setImageFirst(imageName);
            if(counter==1)objectEntity.setImageSecond(imageName);
            if(counter==2)objectEntity.setImageThird(imageName);

            counter++;
        }


        objectEntity.setDateOfAddition(LocalDateTime.now());
        Object saveObject = objectRepository.save(objectEntity);

        String uploadDir = "./uploads/" + saveObject.getId();

        for (MultipartFile multipartFile:multipartFiles){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            Image.saveFile(uploadDir,multipartFile,fileName);
        }
        return "redirect:/objects";
    }
}