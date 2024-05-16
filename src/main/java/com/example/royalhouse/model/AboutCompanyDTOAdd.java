package com.example.royalhouse.model;

import com.example.royalhouse.enums.SectionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutCompanyDTOAdd {
    private Long id;
    private SectionType type;
    private String text;
    private String title;
    private String description;
    private String banner;
    private String image1;
    private String image2;
    private MultipartFile bannerMF;
    private MultipartFile image1MF;
    private MultipartFile image2MF;

    public String linkToBanner() {
        return "/uploads/banner/about-company/" + id + "/banner/" + banner;
    }

    public String linkToImage1() {
        return "/uploads/banner/about-company/" + id + "/images/" + image1;
    }

    public String linkToImage2() {
        return "/uploads/banner/about-company/" + id + "/images/" + image2;
    }
}
