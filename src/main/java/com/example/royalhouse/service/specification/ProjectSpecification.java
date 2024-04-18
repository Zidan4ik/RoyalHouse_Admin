package com.example.royalhouse.service.specification;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.Request;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {
    public static Specification<Project> hasName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),name+"%"));
    }
    public static Specification<Project> hasAddress(String address){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("address"),address+"%"));
    }
    public static Specification<Project> isActive(boolean isActive) {
        return ((root, query, criteriaBuilder) ->
                isActive ? criteriaBuilder.isTrue(root.get("isActive")) : criteriaBuilder.isFalse(root.get("isActive")));
    }

}
