package com.example.royalhouse.repo;

import com.example.royalhouse.entity.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRequestRepository extends JpaRepository<EmailRequest, Long> {
}
