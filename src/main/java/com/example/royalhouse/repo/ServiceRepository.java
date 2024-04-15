package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Service_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository extends JpaRepository<Service_,Long>, JpaSpecificationExecutor<Service_> {
}
