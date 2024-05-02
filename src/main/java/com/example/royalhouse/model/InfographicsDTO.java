package com.example.royalhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfographicsDTO {
    private Long id;
    private String linkToImage;
    private MultipartFile multipartFile;
    private String description;

    public InfographicsDTO(Long id, String linkToImage, String description) {
        this.id = id;
        this.linkToImage = linkToImage;
        this.description = description;
    }
}
