package com.example.royalhouse.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceDTOAdd {
    private Long id;
    @NotBlank(message = "Введіть назву для послуги")
    private String name;
    private String description;
    private boolean isReflection;
    private String image;
    private String banner;
    public String getLinkToImage() {
        return "/uploads/service/" + id + "/" + image;
    }

    public String getLinkToBanner() {
        return "/uploads/service/" + id + "/" + banner;
    }
}
