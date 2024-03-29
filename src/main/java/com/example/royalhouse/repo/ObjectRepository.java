package com.example.royalhouse.repo;

import com.example.royalhouse.entity.Object;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends CrudRepository<Object,Long> {

}
