package com.example.royalhouse.model;

import com.example.royalhouse.enums.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDTOView {
    private Long id;
    private Building building;
    private double area;
    private double price;
    private double priceSquareMeter;
    private int rooms;
    private int storey;
    private int countStoreys;
    private LocalDateTime dateOfAddition;
}