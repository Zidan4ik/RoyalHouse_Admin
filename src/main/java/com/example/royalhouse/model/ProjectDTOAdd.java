package com.example.royalhouse.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProjectDTOAdd {
    private Long id;
    private String name;
    private String address;
    private Integer indexNum;
    private boolean isActive;
    private String banner;
    private MultipartFile bannerMF;
    private Double length;
    private Double width;

    private String imagePanorama;
    private MultipartFile panorama;

    private TextDTO descriptionAbout;
    private TextDTO descriptionDestination;
    private TextDTO descriptionInfrastructure;
    private TextDTO descriptionApartment;

    private List<SpecificationTextDTO> textSpecifications;

    private ImagesDTO imagesAboutDTO;
    private MultipartFile[] imagesAbout;

    private ImagesDTO imagesInfrastructureDTO;
    private MultipartFile[] imagesInfrastructure;

    private ImagesDTO imagesApartmentDTO;
    private MultipartFile[] imagesApartment;

    private List<InfographicsDTO> infographicsMain;
    private List<InfographicsDTO> infographicsInfrastructure;
    private List<InfographicsDTO> infographicsApartment;
}
