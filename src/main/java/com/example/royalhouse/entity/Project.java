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
    private String name;
    private String address;
    private boolean isActive;
    private String banner;
    @Column(nullable = true)
    private Integer length;
    @Column(nullable = true)
    private Integer width;
    @Column(nullable = true)
    private Integer indexNum;

}

