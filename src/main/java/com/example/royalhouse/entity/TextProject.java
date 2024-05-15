package com.example.royalhouse.entity;

import com.example.royalhouse.enums.TextType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "texts_projects")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TextProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TextType type;
    @Lob
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    private Project project;
}
