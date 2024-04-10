package com.example.royalhouse.model;

import com.example.royalhouse.enums.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDTOView {
    private Long id;
    private String building;
    private double area;
    private double price;
    private double priceSquareMeter;
    private int rooms;
    private int storey;
    private int countStoreys;
    private String dateOfAddition;
}
