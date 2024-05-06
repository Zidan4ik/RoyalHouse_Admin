package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Section;
import com.example.royalhouse.model.ServiceBannerDTOEdit;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceBanner {
    public static ServiceBannerDTOEdit toDTOEdit(Section entity){
        ServiceBannerDTOEdit dto = new ServiceBannerDTOEdit();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setBanner(entity.getBanner());
        dto.setText(entity.getText());
        dto.setTitle(entity.getTitle());
        return dto;
    }
    public static Section toEntityEdit(ServiceBannerDTOEdit dto){
        Section entity = new Section();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setBanner(dto.getBanner());
        entity.setText(dto.getText());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
