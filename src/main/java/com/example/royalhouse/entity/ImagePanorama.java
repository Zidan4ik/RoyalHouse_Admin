package com.example.royalhouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images_panorama")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImagePanorama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    @OneToOne
    private Project project;
}
