package com.example.royalhouse.model;

import com.example.royalhouse.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingProjectDTO {
    private List<Project> projects;
    private Project project1;
    private Project project2;
    private Project project3;
    private Project project4;
    private Project project5;
    private Project project6;
    private Project project7;
    private Project project8;

    public BindingProjectDTO(List<Project> projects) {
        this.projects = projects;
    }
}
