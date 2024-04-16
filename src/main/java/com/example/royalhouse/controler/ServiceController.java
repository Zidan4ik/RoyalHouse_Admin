package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Service_;
import com.example.royalhouse.mapper.TransferService;
import com.example.royalhouse.model.ServiceDTOAdd;
import com.example.royalhouse.model.ServiceDTOView;
import com.example.royalhouse.service.serviceimp.ObjectServiceImp;
import com.example.royalhouse.service.serviceimp.RequestServiceImp;
import com.example.royalhouse.service.serviceimp.ServiceServiceImp;
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

import java.util.List;

@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceServiceImp service;
    private final ObjectServiceImp objectService;
    private final RequestServiceImp requestService;

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
    public ModelAndView addServiceGM(@ModelAttribute(name = "service") ServiceDTOAdd serviceDTOAdd) {
        ModelAndView model = new ModelAndView("service/services-add");
        return model;
    }

    @PostMapping("/add")
    public ModelAndView addServicePM(@ModelAttribute(name = "service") @Valid ServiceDTOAdd serviceDTOAdd,
                                     BindingResult bindingResult,
                                     @RequestParam(name = "image2") MultipartFile image,
                                     @RequestParam(name = "banner2") MultipartFile banner) {
        ModelAndView model = new ModelAndView();
        Service_ serviceEntity = TransferService.toEntityAdd(serviceDTOAdd);

        if (bindingResult.hasErrors()) {
            model.setViewName("service/services-add");
            return model;
        }

        service.save(serviceEntity, image, banner);
        model.setViewName("redirect:/service");
        return model;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView removeService(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("redirect:/service");
        service.deleteById(id);
        return model;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateServiceGM(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView("service/services-update");
        ServiceDTOAdd serviceBD = TransferService.toDTOAdd(service.getById(id).get());

        model.addObject("service", serviceBD);
        return model;
    }

    @PostMapping("/{id}/update")
    public ModelAndView updateServicePM(@ModelAttribute(name = "service") @Valid ServiceDTOAdd serviceDTOAdd,
                                        BindingResult bindingResult,
                                        @RequestParam(name = "image2") MultipartFile image,
                                        @RequestParam(name = "banner2") MultipartFile banner) {
        ModelAndView model = new ModelAndView();
        if(bindingResult.hasErrors()){
            model.setViewName("service/services-update");
            return model;
        }

        service.update(TransferService.toEntityAdd(serviceDTOAdd),image,banner);
        model.setViewName("redirect:/service");
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
