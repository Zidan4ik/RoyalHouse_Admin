package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.SecondaryMarketBanner;
import com.example.royalhouse.repo.SecondaryMarketRepository;
import com.example.royalhouse.service.SecondaryMarketService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecondaryMarketServiceImp implements SecondaryMarketService {
    private final SecondaryMarketRepository secondaryMarketRepository;
    @Override
    public Optional<SecondaryMarketBanner> getById(Long id) {
        return secondaryMarketRepository.findById(id);
    }

    @Override
    public void save(SecondaryMarketBanner object, MultipartFile image) {
        String uploadDir;
        String imageName = null;
        if (object.getId() != null) {
            Optional<SecondaryMarketBanner> sectionById = secondaryMarketRepository.findById(object.getId());
            object.setImage(sectionById.get().getImage());
        }
        if (!image.isEmpty()) {
            imageName = UUID.randomUUID() + "." + StringUtils.cleanPath(image.getOriginalFilename());
            object.setImage(imageName);
        }

        secondaryMarketRepository.save(object);

        try {
            if (!image.getOriginalFilename().isEmpty()) {
                uploadDir = "./uploads/banner/secondary-market/" + object.getId();
                Image.saveAfterDelete(uploadDir, image, imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SecondaryMarketBanner> getAll() {
        return secondaryMarketRepository.findAll();
    }
}
