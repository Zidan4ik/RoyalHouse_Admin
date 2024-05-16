package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import com.example.royalhouse.repo.ObjectRepository;
import com.example.royalhouse.service.ObjectService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ObjectServiceImp implements ObjectService {

    private final ObjectRepository objectRepository;

    @Override
    public void save(Object object, MultipartFile[] multipartFiles) {
        int counter = 0;
        HashMap<String, MultipartFile> map = new HashMap<>();
        for (MultipartFile file : multipartFiles) {
            String imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(file.getOriginalFilename());
            map.put(imageName, file);
            if (counter == 0 && !file.isEmpty())
                object.setImageFirst(imageName);
            if (counter == 1 && !file.isEmpty())
                object.setImageSecond(imageName);
            if (counter == 2 && !file.isEmpty())
                object.setImageThird(imageName);

            counter++;
        }

        object.setDateOfAddition(LocalDateTime.now());
        objectRepository.save(object);
        String uploadDir = "./uploads/objects/" + object.getId();
        Image.saveFiles(uploadDir, map);
    }

    @Override
    public Page<Object> getAll(Pageable pageable) {
        return objectRepository.findAll(pageable);
    }

    @Override
    public Optional<Object> getById(Long id) {
        return objectRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        objectRepository.deleteById(id);
        Image.fullDelete("./uploads/objects/" + id);
    }

    @Override
    public void update(Object object, MultipartFile[] multipartFiles) {
        HashMap<String, MultipartFile> map = new HashMap<>();
        Object objectFromDB = objectRepository.findById(object.getId()).get();
        List<String> namesDelete = new ArrayList<>();
        if (objectFromDB != null) {

            object.setImageFirst(objectFromDB.getImageFirst());
            object.setImageSecond(objectFromDB.getImageSecond());
            object.setImageThird(objectFromDB.getImageThird());

            int counter = 0;
            for (MultipartFile file : multipartFiles) {
                if (!file.getOriginalFilename().equals("")) {
                    String imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(file.getOriginalFilename());
                    map.put(imageName, file);
                    if (counter == 0) {
                        namesDelete.add(object.getImageFirst());
                        object.setImageFirst(imageName);
                    }
                    if (counter == 1) {
                        namesDelete.add(object.getImageSecond());
                        object.setImageSecond(imageName);
                    }
                    if (counter == 2) {
                        namesDelete.add(object.getImageThird());
                        object.setImageThird(imageName);
                    }
                }
                counter++;
            }
        }
        object.setDateOfAddition(LocalDateTime.now());
        objectRepository.save(object);
        String uploadDir = "./uploads/objects/" + object.getId();
        List<String> namesFiles = map.keySet().stream().toList();
        List<MultipartFile> files = map.values().stream().toList();
        try {
            Image.deleteFiles(uploadDir, namesDelete);
            Image.savesAfterDelete(uploadDir, namesFiles, files);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int getCountObjects() {
        return objectRepository.findAll().size();
    }

    public Page<Object> getFilteredObjects(Integer id, Building typeOfBuilding, Integer rooms, Pageable pageable) {
        Page<Object> pageObjects = objectRepository.findAll(pageable);

        if (id != null && typeOfBuilding == null && rooms == null) {
            pageObjects = objectRepository.findByIdOrBuildingOrRooms(id, null, null, pageable);
        } else if (id == null && typeOfBuilding != null && rooms == null) {
            pageObjects = objectRepository.findByIdOrBuildingOrRooms(null, typeOfBuilding, null, pageable);
        } else if (id == null && typeOfBuilding == null && rooms != null) {
            pageObjects = objectRepository.findByIdOrBuildingOrRooms(null, null, rooms, pageable);
        } else if (id != null && typeOfBuilding != null && rooms == null) {
            pageObjects = objectRepository.findByIdAndBuilding(id, typeOfBuilding, pageable);
        } else if (id == null && typeOfBuilding != null && rooms != null) {
            pageObjects = objectRepository.findByBuildingAndRooms(typeOfBuilding, rooms, pageable);
        } else if (id != null && typeOfBuilding == null && rooms != null) {
            pageObjects = objectRepository.findByIdAndRooms(id, rooms, pageable);
        } else if (id != null && typeOfBuilding != null && rooms != null) {
            pageObjects = objectRepository.findByIdAndBuildingAndRooms(id, typeOfBuilding, rooms, pageable);
        }

        return pageObjects;
    }
}
