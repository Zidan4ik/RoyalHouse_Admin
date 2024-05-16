package com.example.royalhouse.entity;

import  com.example.royalhouse.enums.SectionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sections")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private SectionType type;
    @Column(length = 100)
    private String text;
    @Lob
    @Column(columnDefinition = "text")
    private String description;
    private String banner;
    private String image1;
    private String image2;
    private String title;

}
