package com.example.royalhouse.controler;


import com.example.royalhouse.entity.Object;
import com.example.royalhouse.mapper.TransferObject;
import com.example.royalhouse.model.ObjectDTOAdd;
import com.example.royalhouse.service.serviceimp.ObjectServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/objects")
@RequiredArgsConstructor
public class ControllerObjects {

    private final ObjectServiceImp objectService;
    private final TransferObject transferObject;


    @GetMapping("")
    public ModelAndView viewObjects() {
        ModelAndView model = new ModelAndView();

        model.addObject("objects", transferObject.toTransferDTOViewList(objectService.getAll()));

        model.setViewName("objects/objects-view");
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
                              @RequestParam("extraImage") MultipartFile[] multipartFiles) {
        Object objectEntity = transferObject.toTransferEntityAdd(objectDTOAdd);

        if (bindingResult.hasErrors()) {
            return "objects/objects-add";
        }

        objectService.save(objectEntity, multipartFiles);

        return "redirect:/objects";

    }

    @GetMapping("/{id}/view")
    public ModelAndView viewObjectGet(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("objects/object-view");
        model.addObject("object", transferObject.toTransferDTOView(objectService.getById(id).get()));
        return model;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateObjectGM(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("objects/objects-update");
        model.addObject("object", transferObject.toTransferDTOAdd(objectService.getById(id).get()));
        return model;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateObjectGM(@ModelAttribute(name = "object") @Valid ObjectDTOAdd objectDTOAdd,
                                       BindingResult bindingResult,
                                       @RequestParam("extraImage") MultipartFile[] multipartFiles) {

        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.setViewName("objects/objects-update");
            return model;
        }
        objectService.update(transferObject.toTransferEntityAdd(objectDTOAdd), multipartFiles);
        model.setViewName("redirect:/objects");
        return model;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteObject(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("redirect:/objects");
        objectService.deleteById(id);

        return model;
    }

    @ModelAttribute("count")
    public int showCountObjects() {
        return objectService.getAll().size();
    }
}