package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Object;
import com.example.royalhouse.enums.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {
    Page<Object> findByIdOrBuildingOrRooms(Integer id, Building type,Integer rooms, Pageable pageable);
}
