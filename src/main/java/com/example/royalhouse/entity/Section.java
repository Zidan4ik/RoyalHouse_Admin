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
    @Column(length = 40)
    private String text;
    @Column(length = 215)
    private String description;
    private String banner;
    private String title;
}
