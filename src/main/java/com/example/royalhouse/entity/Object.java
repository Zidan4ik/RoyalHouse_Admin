package com.example.royalhouse.entity;


import com.example.royalhouse.enums.Building;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "objects")
@NoArgsConstructor
@Data
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Building building;
    private double area;
    private double price;
    private double priceSquareMeter;
    private int rooms;
    private int storey;
    private int countStoreys;
    private LocalDateTime dateOfAddition;

    @Column(nullable = true)
    private String imageFirst;
    @Column(nullable = true)
    private String imageSecond;
    @Column(nullable = true)
    private String imageThird;
    @ManyToOne
    private Project project;

}
