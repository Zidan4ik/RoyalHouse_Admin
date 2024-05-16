package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.model.ObjectDTOAdd;
import com.example.royalhouse.model.ObjectDTOView;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperObject {
    public static Object toEntityAdd(ObjectDTOAdd dto) {
        Object entity = new Object();
        entity.setId(dto.getId());
        entity.setBuilding(dto.getBuilding());
        entity.setArea(dto.getArea());
        entity.setPrice(dto.getPrice());
        entity.setPriceSquareMeter(dto.getPriceSquareMeter());
        entity.setRooms(dto.getRooms());
        entity.setStorey(dto.getStorey());
        entity.setCountStoreys(dto.getCountStoreys());
        entity.setImageFirst(dto.getImageFirst());
        entity.setImageSecond(dto.getImageSecond());
        entity.setImageThird(dto.getImageThird());
        entity.setProject(dto.getProject());

        return entity;
    }

    public static ObjectDTOAdd toDTOAdd(Object entity) {
        ObjectDTOAdd dto = new ObjectDTOAdd();
        dto.setId(entity.getId());
        dto.setBuilding(entity.getBuilding());
        dto.setArea(entity.getArea());
        dto.setPrice(entity.getPrice());
        dto.setPriceSquareMeter(entity.getPriceSquareMeter());
        dto.setRooms(entity.getRooms());
        dto.setStorey(entity.getStorey());
        dto.setCountStoreys(entity.getCountStoreys());
        dto.setImageFirst(entity.getImageFirst());
        dto.setImageSecond(entity.getImageSecond());
        dto.setImageThird(entity.getImageThird());
        dto.setProject(entity.getProject());

        return dto;
    }

    public static ObjectDTOView toDTOView(Object object) {
        ObjectDTOView objectDTOView = new ObjectDTOView();

        objectDTOView.setId(object.getId());
        if (object.getBuilding().equals(Building.house)) {
            objectDTOView.setBuilding("Будинок");
        } else if (object.getBuilding().equals(Building.apartment)) {
            objectDTOView.setBuilding("Квартира");
        } else if (object.getBuilding().equals(Building.plot)) {
            objectDTOView.setBuilding("Ділянка");
        } else if (object.getBuilding().equals(Building.commercial)) {
            objectDTOView.setBuilding("Комерційний");
        }
        objectDTOView.setArea(object.getArea());
        objectDTOView.setPrice(object.getPrice());
        objectDTOView.setPriceSquareMeter(object.getPriceSquareMeter());
        objectDTOView.setRooms(object.getRooms());
        objectDTOView.setStorey(object.getStorey());
        objectDTOView.setCountStoreys(object.getCountStoreys());


        objectDTOView.setDateOfAddition(object.getDateOfAddition().format(DateTimeFormatter.ofPattern("yyyy.MM.dd - HH:mm:ss")));

        return objectDTOView;
    }

    public static List<ObjectDTOView> toDTOViewList(List<Object> objectsEntity) {
        return objectsEntity.stream()
                .map(MapperObject::toDTOView)
                .collect(Collectors.toList());
    }
}
