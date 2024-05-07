package com.example.royalhouse.model;

import com.example.royalhouse.entity.SecondaryMarketBanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecondaryMarketDTOEdit {
    private List<SecondaryMarketBanner> info;
    private MultipartFile[] imagesMF;
    public String linkToImage(int index) {
        return "/uploads/banner/secondary-market/" + info.get(index).getId() + "/" + info.get(index).getImage();
    }
}
