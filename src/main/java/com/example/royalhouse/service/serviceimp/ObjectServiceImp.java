package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Object;
import com.example.royalhouse.repo.ObjectRepository;
import com.example.royalhouse.service.ObjectService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
        Image.saveFiles(object.getId(), map);
    }
    @Override
    public List<Object> getAll() {
        return objectRepository.findAll();
    }

    @Override
    public Page<Object> getAllPagination(Pageable pageable) {
        return objectRepository.findAll(pageable);
    }

    @Override
    public Optional<Object> getById(Long id) {
        return objectRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        objectRepository.deleteById(id);
    }

    @Override
    public void update(Object object, MultipartFile[] multipartFiles) {
        HashMap<String, MultipartFile> map = new HashMap<>();
        Object objectFromDB = objectRepository.findById(object.getId()).get();
        if (objectFromDB != null) {

            object.setImageFirst(objectFromDB.getImageFirst());
            object.setImageSecond(objectFromDB.getImageSecond());
            object.setImageThird(objectFromDB.getImageThird());

            int counter = 0;
            for (MultipartFile file : multipartFiles) {
                if(!file.getOriginalFilename().equals("")){
                    String imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(file.getOriginalFilename());
                    map.put(imageName, file);
                    if (counter==0 && objectFromDB.getImageFirst()==null)
                        object.setImageFirst(imageName);
                    if (counter==1 && objectFromDB.getImageSecond()==null)
                        object.setImageSecond(imageName);
                    if (counter==2 && objectFromDB.getImageThird()==null)
                        object.setImageThird(imageName);
                }
                counter++;
            }
        }
        object.setDateOfAddition(LocalDateTime.now());
        objectRepository.save(object);
        Image.saveFiles(object.getId(), map);
    }
}
