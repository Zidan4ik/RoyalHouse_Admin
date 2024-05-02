package com.example.royalhouse.entity;

import com.example.royalhouse.enums.InfographicsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "infographics_projects")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfographicsProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InfographicsType type;
    private String path;
    private String description;
    @ManyToOne
    private Project project;
}
