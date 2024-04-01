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
    private int id;
    @Column(unique = true)
    private InfographicsType type;
    private String path;
    @ManyToOne
    private Project infographics;
}
