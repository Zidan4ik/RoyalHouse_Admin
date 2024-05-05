package com.example.royalhouse.entity.unifier;

import com.example.royalhouse.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectUnifier {
    private Project project;
    private List<InfographicsProjects> infographicsProjectsList;
    private List<ImagesProject> imagesProjectList;
    private List<TextProject> textProjectList;
    private List<SpecificationTextProject> specificationTextsList;
}
