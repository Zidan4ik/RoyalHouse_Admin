package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Service_;
import com.example.royalhouse.repo.ServiceRepository;
import com.example.royalhouse.service.ServiceService;
import com.example.royalhouse.service.specification.ServiceSpecification;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {
    private final ServiceRepository serviceRepository;
    @Override
    public void save(Service_ service, MultipartFile image, MultipartFile banner) {

        serviceRepository.save(service);
    }

    @Override
    public Optional<Service_> getById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public void update(Service_ service, MultipartFile image, MultipartFile banner) {
        serviceRepository.save(service);
    }

    @Override
    public Page<Service_> getAll(String name, Boolean isReflection, Pageable pageable) {
        Specification<Service_> filter = Specification.where(StringUtils.isBlank(name)?null: ServiceSpecification.getName(name))
                .and(isReflection==null?null: ServiceSpecification.getIsReflection(isReflection));
        return serviceRepository.findAll(filter,pageable);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }
}
