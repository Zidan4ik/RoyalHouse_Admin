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
    public Object toTransferEntityAdd(ObjectDTOAdd objectDTO) {
        Object object = new Object();
        object.setId(objectDTO.getId());
        object.setBuilding(objectDTO.getBuilding());
        object.setArea(objectDTO.getArea());
        object.setPrice(objectDTO.getPrice());
        object.setPriceSquareMeter(objectDTO.getPriceSquareMeter());
        object.setRooms(objectDTO.getRooms());
        object.setStorey(objectDTO.getStorey());
        object.setCountStoreys(objectDTO.getCountStoreys());
        object.setImageFirst(objectDTO.getImageFirst());
        object.setImageSecond(objectDTO.getImageSecond());
        object.setImageThird(objectDTO.getImageThird());

        return object;
    }

    public ObjectDTOAdd toTransferDTOAdd(Object object) {
        ObjectDTOAdd objectDTOAdd = new ObjectDTOAdd();
        objectDTOAdd.setId(object.getId());
        objectDTOAdd.setBuilding(object.getBuilding());
        objectDTOAdd.setArea(object.getArea());
        objectDTOAdd.setPrice(object.getPrice());
        objectDTOAdd.setPriceSquareMeter(object.getPriceSquareMeter());
        objectDTOAdd.setRooms(object.getRooms());
        objectDTOAdd.setStorey(object.getStorey());
        objectDTOAdd.setCountStoreys(object.getCountStoreys());
        objectDTOAdd.setImageFirst(object.getImageFirst());
        objectDTOAdd.setImageSecond(object.getImageSecond());
        objectDTOAdd.setImageThird(object.getImageThird());

        return objectDTOAdd;
    }

    public static ObjectDTOView toTransferDTOView(Object object) {
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

    public List<ObjectDTOView> toTransferDTOViewList(List<Object> objectsEntity) {
        return objectsEntity.stream()
                .map(MapperObject::toTransferDTOView)
                .collect(Collectors.toList());
    }
}
