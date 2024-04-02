package com.example.royalhouse.controler;


import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.mapper.TransferObject;
import com.example.royalhouse.model.ObjectDTOAdd;
import com.example.royalhouse.model.ObjectDTOView;
import com.example.royalhouse.repo.ObjectRepository;
import com.example.royalhouse.util.Image;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/objects")
@AllArgsConstructor
public class ControllerObjects {

    private ObjectRepository objectRepository;

    private TransferObject transferObject;

    @GetMapping("")
    public ModelAndView viewObjects() {
        ModelAndView model = new ModelAndView("objects/objects-view");
        Iterable<Object> objectsFromDB = objectRepository.findAll();
        model.addObject("objects", objectsFromDB);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView addObjectGM(@ModelAttribute("object") ObjectDTOAdd objectDTOAdd) {
        ModelAndView model = new ModelAndView("objects/objects-add");
        return model;
    }

    @PostMapping()
    public String addObjectPM(@ModelAttribute("object") @Valid ObjectDTOAdd objectDTOAdd,
                              BindingResult bindingResult,
                              @RequestParam("extraImage") MultipartFile[] multipartFiles) throws IOException {
        Object objectEntity = transferObject.toTransferEntityAdd(objectDTOAdd);

        if (bindingResult.hasErrors()) {
            return "objects/objects-add";
        }

        int counter = 0;
        for (MultipartFile file : multipartFiles) {
            String imageName = StringUtils.cleanPath(file.getOriginalFilename());

            if (counter == 0) objectEntity.setImageFirst(imageName);
            if (counter == 1) objectEntity.setImageSecond(imageName);
            if (counter == 2) objectEntity.setImageThird(imageName);

            counter++;
        }
        objectEntity.setDateOfAddition(LocalDateTime.now());
        Object saveObject = objectRepository.save(objectEntity);

        String uploadDir = "./uploads/" + saveObject.getId();

        for (MultipartFile multipartFile : multipartFiles) {
            if(multipartFile!=null){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                Image.saveFile(uploadDir, multipartFile, fileName);
            }
        }
        return "redirect:/objects";

    }
}