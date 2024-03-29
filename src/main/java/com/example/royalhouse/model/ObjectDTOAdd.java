package com.example.royalhouse.model;

import com.example.royalhouse.enums.Building;
import lombok.Data;

@Data
public class ObjectDTOAdd {
    private Long id;
    private Building building;
    private double area;
    private double price;
    private double priceSquareMeter;
    private int rooms;
    private int storey;
    private int countStoreys;
    private String imageFirst;
    private String imageSecond;
    private String imageThird;
}
