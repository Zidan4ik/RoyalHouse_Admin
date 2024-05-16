package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.model.AboutCompanyDTOAdd;
import org.springframework.stereotype.Service;

@Service
public class MapperCompanyBanner {
    public static AboutCompanyDTOAdd toDTOAdd(Section entity){
        AboutCompanyDTOAdd dto = new AboutCompanyDTOAdd();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setBanner(entity.getBanner());
        dto.setType(entity.getType());
        dto.setImage1(entity.getImage1());
        dto.setImage2(entity.getImage2());
        return dto;
    }

    public static Section toEntityAdd(AboutCompanyDTOAdd dto){
        Section entity = new Section();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setBanner(dto.getBanner());
        entity.setType(dto.getType());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        return entity;
    }
}
