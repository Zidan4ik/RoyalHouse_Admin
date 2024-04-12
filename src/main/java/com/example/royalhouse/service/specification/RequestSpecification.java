package com.example.royalhouse.service.specification;

import com.example.royalhouse.entity.Request;
import org.springframework.data.jpa.domain.Specification;

public class RequestSpecification {
    public static Specification<Request> hasFullName(String fullName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%"));
    }

    public static Specification<Request> hasPhone(String phone) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("phone"), phone + "%"));
    }

    public static Specification<Request> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), email + "%"));
    }

    public static Specification<Request> isReported(boolean isReported) {
        return ((root, query, criteriaBuilder) ->
                isReported ? criteriaBuilder.isTrue(root.get("isReported")) : criteriaBuilder.isFalse(root.get("isReported")));
    }
}
