package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Service_;
import com.example.royalhouse.repo.ServiceRepository;
import com.example.royalhouse.service.ServiceService;
import com.example.royalhouse.service.specification.ServiceSpecification;
import com.example.royalhouse.util.Image;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {
    private final ServiceRepository serviceRepository;

    @SneakyThrows
    @Override
    public void save(Service_ service, MultipartFile image, MultipartFile banner) {
        String uploadDir;
        String imageName = null;
        String bannerName = null;

        if (!image.isEmpty()) {
            imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(image.getOriginalFilename());
            service.setImage(imageName);
        }

        if (!banner.isEmpty()) {
            bannerName = UUID.randomUUID() + "." + StringUtils.cleanPath(banner.getOriginalFilename());
            service.setBanner(bannerName);
        }
        service.setDateOfAddition(LocalDateTime.now());
        serviceRepository.save(service);

        if (!image.getOriginalFilename().isEmpty()) {
            uploadDir = "./uploads/service/image/" + service.getId();
            Image.saveFile(uploadDir, image, imageName);
        }
        if (!banner.getOriginalFilename().isEmpty()) {
            uploadDir = "./uploads/service/banner/" + service.getId();
            Image.saveFile(uploadDir, banner, bannerName);
        }
    }

    @Override
    public Optional<Service_> getById(Long id) {
        return serviceRepository.findById(id);
    }

    @SneakyThrows
    @Override
    public void update(Service_ service, MultipartFile image, MultipartFile banner) {
        String uploadDir;
        String imageName = null;
        String bannerName = null;

        Optional<Service_> serviceBD = serviceRepository.findById(service.getId());

        if(serviceBD.get()!=null){
            service.setImage(serviceBD.get().getImage());
            service.setBanner(serviceBD.get().getBanner());
        }

        if (!image.isEmpty()) {
            imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(image.getOriginalFilename());
            service.setImage(imageName);
        }

        if (!banner.isEmpty()) {
            bannerName = UUID.randomUUID() + "." + StringUtils.cleanPath(banner.getOriginalFilename());
            service.setBanner(bannerName);
        }
        service.setDateOfAddition(LocalDateTime.now());
        serviceRepository.save(service);
        uploadDir = "./uploads/service/" + service.getId();

        if (!image.getOriginalFilename().isEmpty()) {
            Image.saveFile(uploadDir, image, imageName);
        }
        if (!banner.getOriginalFilename().isEmpty()) {
            Image.saveFile(uploadDir, banner, bannerName);
        }

    }

    @Override
    public Page<Service_> getAll(String name, Boolean isReflection, Pageable pageable) {
        Specification<Service_> filter = Specification.where(io.micrometer.common.util.StringUtils.isBlank(name) ? null : ServiceSpecification.getName(name))
                .and(isReflection == null ? null : ServiceSpecification.getIsReflection(isReflection));
        return serviceRepository.findAll(filter, pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }
}
