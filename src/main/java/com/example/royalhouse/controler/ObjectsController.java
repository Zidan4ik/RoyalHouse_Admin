package com.example.royalhouse.controler;


import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.mapper.MapperObject;
import com.example.royalhouse.model.ObjectDTOAdd;
import com.example.royalhouse.service.serviceimp.ObjectServiceImp;
import com.example.royalhouse.service.serviceimp.ProjectServiceImp;
import com.example.royalhouse.service.serviceimp.RequestServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/admin/objects")
@RequiredArgsConstructor
public class ObjectsController {

    private final ObjectServiceImp objectService;
    private final RequestServiceImp requestService;
    private final ProjectServiceImp projectServiceImp;
    @GetMapping("")
    public ModelAndView viewObjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name="building", required = false) Building typeOfBuilding,
            @RequestParam(name="rooms",required = false) Integer rooms) {
        ModelAndView model = new ModelAndView("objects/objects-view");

        Pageable paging = PageRequest.of(page, size);
        Page<Object> pageObjects = objectService.getFilteredObjects(id,typeOfBuilding,rooms,paging);

        model.addObject("objectsDB", MapperObject.toDTOViewList(pageObjects.getContent()));
        model.addObject("objects", pageObjects);
        model.addObject("currentPage", page);

        model.addObject("id",id);
        model.addObject("typeOfBuilding",typeOfBuilding);
        model.addObject("rooms",rooms);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView addObjectGM(@ModelAttribute("object") ObjectDTOAdd objectDTOAdd) {
        ModelAndView model = new ModelAndView("objects/objects-add");
        model.addObject("projects",projectServiceImp.getAll());
        return model;
    }

    @PostMapping("/add")
    public ModelAndView addObjectPM(@ModelAttribute("object") @Valid ObjectDTOAdd objectDTOAdd,
                              BindingResult bindingResult,
                              @RequestParam("extraImage") MultipartFile[] multipartFiles) {
        ModelAndView model = new ModelAndView();
        Object objectEntity = MapperObject.toEntityAdd(objectDTOAdd);
        if (bindingResult.hasErrors()) {
            model.addObject("projects",projectServiceImp.getAll());
            model.setViewName("objects/objects-add");
            return model;
        }

        objectService.save(objectEntity, multipartFiles);
        model.setViewName("redirect:/admin/objects");
        return model;

    }

    @GetMapping("/{id}/view")
    public ModelAndView viewObjectGet(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("objects/object-view");
        model.addObject("object", MapperObject.toDTOView(objectService.getById(id).get()));
        return model;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateObjectGM(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("objects/objects-update");
        model.addObject("object", MapperObject.toDTOAdd(objectService.getById(id).get()));
        model.addObject("projects",projectServiceImp.getAll());
        return model;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateObjectGM(@ModelAttribute(name = "object") @Valid ObjectDTOAdd objectDTOAdd,
                                       BindingResult bindingResult,
                                       @RequestParam("extraImage") MultipartFile[] multipartFiles) {

        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.addObject("projects",projectServiceImp.getAll());
            model.setViewName("objects/objects-update");
            return model;
        }
        objectService.update(MapperObject.toEntityAdd(objectDTOAdd), multipartFiles);
        model.setViewName("redirect:/admin/objects");
        return model;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteObject(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("redirect:/admin/objects");
        objectService.deleteById(id);
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
    @ModelAttribute("isActiveObjects")
    public boolean toActiveContacts(){
        return true;
    }
}