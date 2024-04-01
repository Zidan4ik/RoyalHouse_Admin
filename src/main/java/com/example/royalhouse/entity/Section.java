package com.example.royalhouse.entity;

import  com.example.royalhouse.enums.SectionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private SectionType type;
    private String pathToFile;
    private String text;
    private String title;
    private String description;
}
