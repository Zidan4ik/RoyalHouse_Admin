package com.example.royalhouse.model;

import com.example.royalhouse.enums.SectionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceBannerDTOEdit {
    private Long id;
    private SectionType type;
    private String banner;
    private MultipartFile bannerMF;
    private String text;
    private String title;

    public String linkToBanner(){
        return "/uploads/banner/service/"+id+"/"+banner;
    }
}
