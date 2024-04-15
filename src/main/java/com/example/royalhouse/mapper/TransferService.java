package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.Service_;
import com.example.royalhouse.model.ServiceDTOAdd;
import com.example.royalhouse.model.ServiceDTOView;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    public static ServiceDTOView toDTOView(Service_ service) {
        ServiceDTOView serviceDTOView = new ServiceDTOView();
        serviceDTOView.setId(service.getId());
        serviceDTOView.setName(service.getName());
        serviceDTOView.setDescription(service.getDescription());
        serviceDTOView.setReflection(service.isReflection());
        if(service.getDateOfAddition()!=null)
        serviceDTOView.setDate(service.getDateOfAddition().format(DateTimeFormatter.ofPattern("yyyy.MM.dd - HH:mm:ss")));
        return serviceDTOView;
    }

    public static ServiceDTOAdd toDTOAdd(Service_ service) {
        ServiceDTOAdd serviceDTOAdd = new ServiceDTOAdd();
        serviceDTOAdd.setId(service.getId());
        serviceDTOAdd.setName(service.getName());
        serviceDTOAdd.setDescription(service.getDescription());
        serviceDTOAdd.setReflection(service.isReflection());
        serviceDTOAdd.setBanner(service.getBanner());
        serviceDTOAdd.setImage(service.getImage());
        return serviceDTOAdd;
    }
    public static Service_ toEntityAdd(ServiceDTOAdd serviceDTOAdd) {
        Service_ service = new Service_();
        service.setId(serviceDTOAdd.getId());
        service.setName(serviceDTOAdd.getName());
        service.setDescription(serviceDTOAdd.getDescription());
        service.setReflection(serviceDTOAdd.isReflection());
        service.setBanner(serviceDTOAdd.getBanner());
        service.setImage(serviceDTOAdd.getImage());
        return service;
    }
    public static List<ServiceDTOView> toDTOViewList(List<Service_> services){
        return services.stream()
                .map(TransferService::toDTOView)
                .collect(Collectors.toList());
    }
}
