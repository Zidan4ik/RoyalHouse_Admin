package com.example.royalhouse.entity;

import com.example.royalhouse.enums.ImageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images_projects")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private ImageType type;

    private String imageFirst;
    private String imageSecond;
    private String imageThird;

    @ManyToOne
    private Project project;
}
