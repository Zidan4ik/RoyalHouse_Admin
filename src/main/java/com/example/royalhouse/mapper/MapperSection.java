package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.model.AboutCompanyDTOAdd;
import org.springframework.stereotype.Service;

@Service
public class MapperSection {
    public static AboutCompanyDTOAdd toDTOAdd(Section section){
        AboutCompanyDTOAdd sectionDTO = new AboutCompanyDTOAdd();
        sectionDTO.setId(section.getId());
        sectionDTO.setText(section.getText());
        sectionDTO.setTitle(section.getTitle());
        sectionDTO.setDescription(section.getDescription());
        sectionDTO.setBanner(section.getBanner());
        sectionDTO.setType(section.getType());
        return sectionDTO;
    }

    public static Section toEntityAdd(AboutCompanyDTOAdd sectionDTO){
        Section section = new Section();
        section.setId(sectionDTO.getId());
        section.setText(sectionDTO.getText());
        section.setTitle(sectionDTO.getTitle());
        section.setDescription(sectionDTO.getDescription());
        section.setBanner(sectionDTO.getBanner());
        section.setType(sectionDTO.getType());
        return section;
    }
}
