package com.example.royalhouse.service;

import com.example.royalhouse.entity.SecondaryMarketBanner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SecondaryMarketService {
    Optional<SecondaryMarketBanner> getById(Long id);
    void save(SecondaryMarketBanner object, MultipartFile multipartFile);
    List<SecondaryMarketBanner> getAll();
}
