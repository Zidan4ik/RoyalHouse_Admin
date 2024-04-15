package com.example.royalhouse.service.specification;

import com.example.royalhouse.entity.Service_;
import org.springframework.data.jpa.domain.Specification;

public class ServiceSpecification {
    public static Specification<Service_> getName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name + "%"));
    }

    public static Specification<Service_> getIsReflection(Boolean isReflection) {
        return ((root, query, criteriaBuilder) -> isReflection ? criteriaBuilder
                .isTrue(root.get("isReflection")) : criteriaBuilder.isFalse(root.get("isReflection")));
    }
}
