package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long>, JpaSpecificationExecutor<Request> {

}
