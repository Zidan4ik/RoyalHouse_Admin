package com.example.royalhouse.service.serviceimp;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImp implements SectionService {
    private final SectionRepository sectionRepository;

    @Override
    public void save(Section section, MultipartFile banner, MultipartFile image1, MultipartFile image2) {
        String uploadDir;
        String bannerName = null;
        String image1Name = null;
        String image2Name = null;
        List<String> image1Delete = new ArrayList<>();
        List<String> image2Delete = new ArrayList<>();
        Optional<Section> sectionById = null;
        if (section.getId() != null) {
            sectionById = sectionRepository.findById(section.getId());
            section.setBanner(sectionById.get().getBanner());
            if (sectionById.get().getType().equals(SectionType.aboutCompany)) {
                section.setImage1(sectionById.get().getImage1());
                section.setImage2(sectionById.get().getImage2());
            }
        }
        if (!banner.isEmpty()) {
            bannerName = UUID.randomUUID() + "." + StringUtils.cleanPath(banner.getOriginalFilename());
            section.setBanner(bannerName);
        }
        if(section.getType().equals(SectionType.aboutCompany)){
            if (!image1.isEmpty()) {
                image1Delete.add(section.getImage1());
                image1Name = UUID.randomUUID() + "." + StringUtils.cleanPath(image1.getOriginalFilename());
                section.setImage1(image1Name);
            }
            if (!image2.isEmpty()) {
                image2Delete.add(section.getImage2());
                image2Name = UUID.randomUUID() + "." + StringUtils.cleanPath(image2.getOriginalFilename());
                section.setImage2(image2Name);
            }
        }

        sectionRepository.save(section);

        try {
            if(section.getType().equals(SectionType.aboutCompany)){
                if (!banner.getOriginalFilename().isEmpty()) {
                    uploadDir = "./uploads/banner/about-company/" + section.getId() + "/banner/";
                    Image.saveAfterDelete(uploadDir, banner, section.getBanner());
                }
                if(!image1.getOriginalFilename().isEmpty()){
                    uploadDir = "./uploads/banner/about-company/" + section.getId() + "/images/";
                    if(sectionById!=null){
                        Image.deleteFiles(uploadDir,image1Delete);
                    }
                    Image.saveFile(uploadDir, image1, section.getImage1());
                }
                if(!image2.getOriginalFilename().isEmpty()){
                    uploadDir = "./uploads/banner/about-company/" + section.getId() + "/images/";
                    if(sectionById!=null){
                        Image.deleteFiles(uploadDir,image2Delete);
                    }
                    Image.saveFile(uploadDir, image2, section.getImage2());

                }
            }
            if (!banner.getOriginalFilename().isEmpty() && section.getType().equals(SectionType.service)) {
                uploadDir = "./uploads/banner/service/" + section.getId();
                Image.saveAfterDelete(uploadDir, banner, bannerName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Section> getById(Long id) {
        return sectionRepository.findById(id);
    }

    public Optional<Section> getByType(SectionType type) {

        Optional<Section> section = sectionRepository.getByType(type);
        return section;
    }
}
