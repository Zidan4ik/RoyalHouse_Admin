package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.enums.SectionType;
import com.example.royalhouse.mapper.MapperCompanyBanner;
import com.example.royalhouse.mapper.MapperServiceBanner;
import com.example.royalhouse.model.AboutCompanyDTOAdd;
import com.example.royalhouse.model.ServiceBannerDTOEdit;
import com.example.royalhouse.service.serviceimp.SectionServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/banner")
@RequiredArgsConstructor
public class SectionController {
    private final SectionServiceImp sectionServiceImp;

    @GetMapping("/company/edit")
    public ModelAndView companyBannerGM() {
        ModelAndView model = new ModelAndView("banner/about-company-edit");
        Optional<Section> sectionAboutCompany = sectionServiceImp.getByType(SectionType.aboutCompany);

        if (sectionAboutCompany.isEmpty()) {
            model.addObject("section", new AboutCompanyDTOAdd());
        } else {
            AboutCompanyDTOAdd dtoAdd = MapperCompanyBanner.toDTOAdd(sectionAboutCompany.get());
            model.addObject("section", dtoAdd);
        }

        return model;
    }

    @PostMapping("/company/edit")
    public ModelAndView companyBannerPM(@ModelAttribute(name = "section") AboutCompanyDTOAdd dto) {
        ModelAndView model = new ModelAndView("redirect:/projects");
        Optional<Section> section = sectionServiceImp.getByType(dto.getType());
        dto.setType(SectionType.aboutCompany);
        if (!section.isEmpty()) {
            dto.setId(section.get().getId());
        }
        sectionServiceImp.save(MapperCompanyBanner.toEntityAdd(dto), dto.getBannerMF());
        return model;
    }

    @GetMapping("/service/edit")
    public ModelAndView serviceBannerGM() {
        ModelAndView model = new ModelAndView("banner/service-edit");
        Optional<Section> sectionAboutCompany = sectionServiceImp.getByType(SectionType.service);

        ServiceBannerDTOEdit dtoEdit;
        if (sectionAboutCompany.isEmpty()) {
            dtoEdit = new ServiceBannerDTOEdit();
        } else {
            dtoEdit = MapperServiceBanner.toDTOEdit(sectionAboutCompany.get());
        }
        model.addObject("section", dtoEdit);
        return model;
    }

    @PostMapping("/service/edit")
    public ModelAndView serviceBannerPM(@ModelAttribute(name = "section") ServiceBannerDTOEdit dto) {
        ModelAndView model = new ModelAndView("redirect:/projects");
        dto.setType(SectionType.service);
        Optional<Section> section = sectionServiceImp.getByType(dto.getType());
        if (!section.isEmpty()) {
            dto.setId(section.get().getId());
        }
        sectionServiceImp.save(MapperServiceBanner.toEntityEdit(dto), dto.getBannerMF());
        return model;
    }
}
