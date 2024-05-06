package com.example.royalhouse.service.serviceimp;

import com.example.royalhouse.entity.Project;
import com.example.royalhouse.entity.Section;
import com.example.royalhouse.enums.SectionType;
import com.example.royalhouse.repo.SectionRepository;
import com.example.royalhouse.service.SectionService;
import com.example.royalhouse.util.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImp implements SectionService {
    private final SectionRepository sectionRepository;
    @Override
    public void save(Section section, MultipartFile banner) {
        String uploadDir;
        String bannerName = null;
        if (section.getId() != null) {
            Optional<Section> sectionById = sectionRepository.findById(section.getId());
            section.setBanner(sectionById.get().getBanner());
        }
        if (!banner.isEmpty()) {
            bannerName = UUID.randomUUID() + "." + StringUtils.cleanPath(banner.getOriginalFilename());
            section.setBanner(bannerName);
        }

        sectionRepository.save(section);

        try {
            if (!banner.getOriginalFilename().isEmpty() && section.getType().equals(SectionType.aboutCompany)) {
                uploadDir = "./uploads/banner/about-company/" + section.getId();
                Image.saveBanner(uploadDir, banner, bannerName);
            }
            if (!banner.getOriginalFilename().isEmpty() && section.getType().equals(SectionType.service)) {
                uploadDir = "./uploads/banner/service/" + section.getId();
                Image.saveBanner(uploadDir, banner, bannerName);
            }
            if (!banner.getOriginalFilename().isEmpty() && section.getType().equals(SectionType.secondaryMarket)) {
                uploadDir = "./uploads/banner/secondary-market/" + section.getId();
                Image.saveBanner(uploadDir, banner, bannerName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Section> getById(Long id) {
        return sectionRepository.findById(id);
    }
    public Optional<Section> getByType(SectionType type){

        Optional<Section> section = sectionRepository.getByType(type);
        return section;
    }
}
