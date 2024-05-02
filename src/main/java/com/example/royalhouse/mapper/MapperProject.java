package com.example.royalhouse.mapper;

import com.example.royalhouse.entity.*;
import com.example.royalhouse.entity.unifier.ProjectUnifier;
import com.example.royalhouse.enums.ImageType;
import com.example.royalhouse.enums.InfographicsType;
import com.example.royalhouse.enums.TextType;
import com.example.royalhouse.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperProject {
    public static ProjectDTOView toDTOView(Project project) {
        ProjectDTOView projectDTOView = new ProjectDTOView();
        projectDTOView.setId(Math.toIntExact(project.getId()));
        projectDTOView.setName(project.getName());
        projectDTOView.setAddress(project.getAddress());
        projectDTOView.setActive(project.isActive());
        projectDTOView.setIndexNum(project.getIndexNum());
        return projectDTOView;
    }

    public static ProjectDTOAdd toDTOAdd(Project project, List<InfographicsProjects> infographics, List<ImagesProject> images,
                                         List<SpecificationTextProject> specificationTexts, List<TextProject> texts) {
        ProjectDTOAdd projectDTOAdd = getProjectDTOAdd(project);

        for (TextProject tp : texts) {
            if (tp.getType().equals(TextType.aboutProject))
                projectDTOAdd.setDescriptionAbout(new TextDTO(tp.getId(), tp.getDescription()));
            if (tp.getType().equals(TextType.destination))
                projectDTOAdd.setDescriptionDestination(new TextDTO(tp.getId(), tp.getDescription()));
            if (tp.getType().equals(TextType.infrastructure))
                projectDTOAdd.setDescriptionInfrastructure(new TextDTO(tp.getId(), tp.getDescription()));
            if (tp.getType().equals(TextType.apartments))
                projectDTOAdd.setDescriptionApartment(new TextDTO(tp.getId(), tp.getDescription()));
        }

        for (ImagesProject i : images) {
            if (i.getType().equals(ImageType.aboutProject)) {
                projectDTOAdd.setImagesAboutDTO(new ImagesDTO(i.getId(), i.getImageFirst(), i.getImageSecond(), i.getImageThird()));
            }
            if (i.getType().equals(ImageType.infrastructure)) {
                projectDTOAdd.setImagesInfrastructureDTO(new ImagesDTO(i.getId(), i.getImageFirst(), i.getImageSecond(), i.getImageThird()));
            }
            if (i.getType().equals(ImageType.apartment)) {
                projectDTOAdd.setImagesApartmentDTO(new ImagesDTO(i.getId(), i.getImageFirst(), i.getImageSecond(), i.getImageThird()));
            }
        }

        List<SpecificationTextDTO> specificTextNew = new ArrayList<>();

        for (SpecificationTextProject st : specificationTexts) {
            specificTextNew.add(new SpecificationTextDTO(st.getId(), st.getMiniText()));
        }

        projectDTOAdd.setTextSpecifications(specificTextNew);

        List<InfographicsDTO> infographicsMain = new ArrayList<>();
        List<InfographicsDTO> infographicsInfrastructure = new ArrayList<>();
        List<InfographicsDTO> infographicsApartments = new ArrayList<>();

        for (InfographicsProjects ip : infographics) {
            if (ip.getType().equals(InfographicsType.main)) {
                infographicsMain.add(new InfographicsDTO(ip.getId(), ip.getPath(), ip.getDescription()));
            }
            if (ip.getType().equals(InfographicsType.infrastructure)) {
                infographicsInfrastructure.add(new InfographicsDTO(ip.getId(), ip.getPath(), ip.getDescription()));
            }
            if (ip.getType().equals(InfographicsType.apartment)) {
                infographicsApartments.add(new InfographicsDTO(ip.getId(), ip.getPath(), ip.getDescription()));
            }
        }
        projectDTOAdd.setInfographicsMain(infographicsMain);
        projectDTOAdd.setInfographicsInfrastructure(infographicsInfrastructure);
        projectDTOAdd.setInfographicsApartment(infographicsApartments);

        return projectDTOAdd;
    }


    public static ProjectUnifier toEntityAdd(ProjectDTOAdd projectDTOAdd) {
        Project project = getProject(projectDTOAdd);

        List<TextProject> textProjectList = new ArrayList<>();
        textProjectList.add(new TextProject(projectDTOAdd.getDescriptionAbout().getId(), TextType.aboutProject, projectDTOAdd.getDescriptionAbout().getText(), project));
        textProjectList.add(new TextProject(projectDTOAdd.getDescriptionDestination().getId(), TextType.destination, projectDTOAdd.getDescriptionDestination().getText(), project));
        textProjectList.add(new TextProject(projectDTOAdd.getDescriptionInfrastructure().getId(), TextType.infrastructure, projectDTOAdd.getDescriptionInfrastructure().getText(), project));
        textProjectList.add(new TextProject(projectDTOAdd.getDescriptionApartment().getId(), TextType.apartments, projectDTOAdd.getDescriptionApartment().getText(), project));

        List<ImagesProject> imagesProjectList = new ArrayList<>();
        ImagesDTO imagesAboutDTO = projectDTOAdd.getImagesAboutDTO();

        if (imagesAboutDTO != null) {
            imagesProjectList.add(new ImagesProject(
                    imagesAboutDTO.getId(),
                    ImageType.aboutProject,
                    imagesAboutDTO.getLinkImage1(),
                    imagesAboutDTO.getLinkImage2(),
                    imagesAboutDTO.getLinkImage3(),
                    project));
        } else {
            imagesProjectList.add(new ImagesProject(null, ImageType.aboutProject, null, null, null, project));
        }
        ImagesDTO imagesInfrastructureDTO = projectDTOAdd.getImagesInfrastructureDTO();
        if (imagesInfrastructureDTO != null) {
            imagesProjectList.add(new ImagesProject(
                    imagesInfrastructureDTO.getId(),
                    ImageType.infrastructure,
                    imagesInfrastructureDTO.getLinkImage1(),
                    imagesInfrastructureDTO.getLinkImage2(),
                    imagesInfrastructureDTO.getLinkImage3(),
                    project));
        } else {
            imagesProjectList.add(new ImagesProject(null, ImageType.infrastructure, null, null, null, project));
        }
        ImagesDTO imagesApartmentDTO = projectDTOAdd.getImagesApartmentDTO();
        if (imagesApartmentDTO != null) {
            imagesProjectList.add(new ImagesProject(
                    imagesApartmentDTO.getId(),
                    ImageType.apartment,
                    imagesApartmentDTO.getLinkImage1(),
                    imagesApartmentDTO.getLinkImage2(),
                    imagesApartmentDTO.getLinkImage3(),
                    project));
        } else {
            imagesProjectList.add(new ImagesProject(null, ImageType.apartment, null, null, null, project));
        }


        List<SpecificationTextProject> specificationTextProjectList = new ArrayList<>();
        for (SpecificationTextDTO t : projectDTOAdd.getTextSpecifications()) {
            specificationTextProjectList.add(new SpecificationTextProject(t.getId(), t.getMiniText(), project));
        }

        List<InfographicsProjects> infographicsProjectsList = new ArrayList<>();

        for (InfographicsDTO i : projectDTOAdd.getInfographicsMain()) {
            infographicsProjectsList.add(new InfographicsProjects(
                    i.getId(),
                    InfographicsType.main,
                    i.getLinkToImage(),
                    i.getDescription(),
                    project));
        }
        for (InfographicsDTO i : projectDTOAdd.getInfographicsInfrastructure()) {
            infographicsProjectsList.add(
                    new InfographicsProjects(
                            i.getId(),
                            InfographicsType.infrastructure,
                            i.getLinkToImage(),
                            i.getDescription(),
                            project));
        }
        for (InfographicsDTO i : projectDTOAdd.getInfographicsApartment()) {
            infographicsProjectsList.add(
                    new InfographicsProjects(
                            i.getId(),
                            InfographicsType.apartment,
                            i.getLinkToImage(),
                            i.getDescription(),
                            project));
        }
        return new ProjectUnifier(project, infographicsProjectsList, imagesProjectList, textProjectList, specificationTextProjectList);
    }

    public static List<ProjectDTOView> toDTOViewList(List<Project> projectList) {
        return projectList.stream()
                .map(MapperProject::toDTOView)
                .collect(Collectors.toList());
    }


    private static Project getProject(ProjectDTOAdd projectDTOAdd) {
        Project project = new Project();
        project.setId(projectDTOAdd.getId());
        project.setName(projectDTOAdd.getName());
        project.setAddress(projectDTOAdd.getAddress());
        project.setActive(projectDTOAdd.isActive());
        project.setBanner(projectDTOAdd.getBanner());
        project.setImagePanorama(projectDTOAdd.getImagePanorama());
        project.setIndexNum(projectDTOAdd.getIndexNum());
        project.setLength(projectDTOAdd.getLength());
        project.setWidth(projectDTOAdd.getWidth());
        return project;
    }

    private static ProjectDTOAdd getProjectDTOAdd(Project project) {
        ProjectDTOAdd projectDTOAdd = new ProjectDTOAdd();
        projectDTOAdd.setId(project.getId());
        projectDTOAdd.setName(project.getName());
        projectDTOAdd.setAddress(project.getAddress());
        projectDTOAdd.setIndexNum(project.getIndexNum());
        projectDTOAdd.setActive(project.isActive());
        projectDTOAdd.setBanner(project.getBanner());
        projectDTOAdd.setLength(project.getLength());
        projectDTOAdd.setWidth(project.getWidth());
        projectDTOAdd.setImagePanorama(project.getImagePanorama());
        return projectDTOAdd;
    }
}
