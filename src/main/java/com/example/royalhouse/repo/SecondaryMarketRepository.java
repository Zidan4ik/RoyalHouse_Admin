package com.example.royalhouse.repo;

import com.example.royalhouse.entity.SecondaryMarketBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryMarketRepository extends JpaRepository<SecondaryMarketBanner,Long> {

}
