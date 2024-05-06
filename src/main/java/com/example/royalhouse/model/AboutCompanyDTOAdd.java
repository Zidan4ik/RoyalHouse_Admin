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
    private MultipartFile bannerMF;

    public String linkToBanner(){
        return "/uploads/banner/about-company/"+id+"/"+banner;
    }
}
