package com.example.royalhouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String name;
    @Lob
    @Column(length = 60)
    private String address;
    private boolean isActive;
    @Column(length = 215)
    private String banner;
    private String imagePanorama;
    private Integer indexNum;
    private Double length;
    private Double width;
    private Integer block;
}

