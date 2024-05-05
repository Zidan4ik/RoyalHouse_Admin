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
    private String length;
    private String width;

    private String panorama;
    private MultipartFile panoramaMF;

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

    public String linkBanner() {
        return "/uploads/project/banner/" + id + "/" + banner;
    }

    public String linkPanorama() {
        return "/uploads/project/panorama/" + id + "/" + panorama;
    }

    public String linkImagesAbout(int index) {
        if (index == 0) {
            return "/uploads/project/images/about/" + imagesAboutDTO.getId() + "/" + imagesAboutDTO.getLinkImage1();
        } else if (index == 1) {
            return  "/uploads/project/images/about/" + imagesAboutDTO.getId() + "/" + imagesAboutDTO.getLinkImage2();
        } else if (index == 2) {
            return  "/uploads/project/images/about/" + imagesAboutDTO.getId() + "/" + imagesAboutDTO.getLinkImage3();
        } else {
            return null;
        }
    }
    public String linkImagesInfrastructure(int index) {
        if (index == 0) {
            return "/uploads/project/images/infrastructure/" + imagesInfrastructureDTO.getId() + "/" + imagesInfrastructureDTO.getLinkImage1();
        } else if (index == 1) {
            return  "/uploads/project/images/infrastructure/" + imagesInfrastructureDTO.getId() + "/" + imagesInfrastructureDTO.getLinkImage2();
        } else if (index == 2) {
            return  "/uploads/project/images/infrastructure/" + imagesInfrastructureDTO.getId() + "/" + imagesInfrastructureDTO.getLinkImage3();
        } else {
            return null;
        }
    }
    public String linkImagesApartment(int index) {
        if (index == 0) {
            return "/uploads/project/images/apartment/" + imagesApartmentDTO.getId() + "/" + imagesApartmentDTO.getLinkImage1();
        } else if (index == 1) {
            return  "/uploads/project/images/apartment/" + imagesApartmentDTO.getId() + "/" + imagesApartmentDTO.getLinkImage2();
        } else if (index == 2) {
            return  "/uploads/project/images/apartment/" + imagesApartmentDTO.getId() + "/" + imagesApartmentDTO.getLinkImage3();
        } else {
            return null;
        }
    }

}
