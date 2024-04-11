package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Request;
import com.example.royalhouse.repo.RequestRepository;
import com.example.royalhouse.service.RequestService;
import com.example.royalhouse.service.specification.RequestSpecification;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImp implements RequestService {
    private final RequestRepository requestRepository;

    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

    public long getCountRequests() {
        return requestRepository.count();
    }

    @Override
    public Page<Request> getAll(String fullName, String phone, String email,Boolean isReported, Pageable pageable) {
        Specification<Request> filters = Specification.where(StringUtils.isBlank(fullName)
                        ? null : RequestSpecification.hasFullName(fullName))
                .and(StringUtils.isBlank(phone) ? null : RequestSpecification.hasPhone(phone))
                .and(StringUtils.isBlank(email) ? null : RequestSpecification.hasEmail(email))
                .and(isReported==null?null:RequestSpecification.isReported(isReported));

        return requestRepository.findAll(filters, pageable);
    }


    @Override
    public Optional<Request> getById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void update(Request object) {
        requestRepository.save(object);
    }
}
