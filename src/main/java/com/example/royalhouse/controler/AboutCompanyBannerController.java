package com.example.royalhouse.controler;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.enums.SectionType;
import com.example.royalhouse.mapper.MapperSection;
import com.example.royalhouse.model.AboutCompanyDTOAdd;
import com.example.royalhouse.service.serviceimp.SectionServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/banner")
@RequiredArgsConstructor
public class AboutCompanyBannerController {
    private final SectionServiceImp sectionServiceImp;

    @GetMapping("/{type}/edit")
    public ModelAndView aboutCompanyGM(@PathVariable(name = "type") SectionType type) {
        ModelAndView model = new ModelAndView("banner/about-company-edit");
        Optional<Section> sectionAboutCompany = sectionServiceImp.getByType(type);

        if (sectionAboutCompany.isEmpty()) {
            model.addObject("section", new AboutCompanyDTOAdd());
        } else {
            AboutCompanyDTOAdd dtoAdd = MapperSection.toDTOAdd(sectionAboutCompany.get());
            model.addObject("section",dtoAdd);
        }

        return model;
    }

    @PostMapping("/{type}/edit")
    public ModelAndView aboutCompanyPM(@ModelAttribute(name = "section") AboutCompanyDTOAdd dto){
        ModelAndView model = new ModelAndView("redirect:/projects");
        dto.setId(sectionServiceImp.getByType(dto.getType()).get().getId());
        sectionServiceImp.save(MapperSection.toEntityAdd(dto),dto.getBannerMF());
        return model;
    }
}
