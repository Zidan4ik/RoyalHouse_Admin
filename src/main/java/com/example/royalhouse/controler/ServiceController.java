package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Service_;
import com.example.royalhouse.mapper.TransferService;
import com.example.royalhouse.model.ServiceDTOAdd;
import com.example.royalhouse.model.ServiceDTOView;
import com.example.royalhouse.service.serviceimp.ServiceServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceServiceImp service;

    @GetMapping("")
    public ModelAndView viewServices(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Boolean isReflection) {
        ModelAndView model = new ModelAndView("service/services-view");
        Pageable pageable = PageRequest.of(page, size);
        Page<Service_> pageService = service.getAll(name, isReflection, pageable);
        List<ServiceDTOView> services = TransferService.toDTOViewList(pageService.getContent());

        model.addObject("services", services);
        model.addObject("pageService", pageService);
        model.addObject("currentPage", page);

        model.addObject("name", name);
        model.addObject("isReflection", isReflection);

        return model;
    }
    @GetMapping("/add")
    public ModelAndView addServiceGM(@ModelAttribute(name = "service")ServiceDTOAdd serviceDTOAdd){
        ModelAndView model = new ModelAndView("service/service-add");
        return model;
    }
    @PostMapping("/add")
    public ModelAndView addServicePM(@ModelAttribute(name = "service") ServiceDTOAdd serviceDTOAdd,
                                     @RequestParam(name = "image") MultipartFile image,
                                     @RequestParam(name = "banner") MultipartFile banner){
        ModelAndView model = new ModelAndView("redirect:/service");
        service.save(TransferService.toEntityAdd(serviceDTOAdd));

        return model;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView removeService(@PathVariable(name = "id") long id){
        ModelAndView model = new ModelAndView("redirect:/service");
        service.deleteById(id);
        return model;
    }
}
