package com.example.royalhouse.entity;


import com.example.royalhouse.enums.Building;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable = true)
    private double area;

    @Column(nullable = true)
    private double price;

    @Column(nullable = true)
    private double priceSquareMeter;

    @Column(nullable = true)
    private int rooms;

    @Column(nullable = true)
    private int storey;

    @Column(nullable = true)
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


    public Object(Building building, double area, double price, double priceSquareMeter, int rooms, int storey, int countStoreys) {
        this.building = building;
        this.area = area;
        this.price = price;
        this.priceSquareMeter = priceSquareMeter;
        this.rooms = rooms;
        this.storey = storey;
        this.countStoreys = countStoreys;
    }

    public String getImagePath() {
        if (imageFirst == null) {
            return null;
        }
        return "/uploads/" + imageFirst;
    }
}
