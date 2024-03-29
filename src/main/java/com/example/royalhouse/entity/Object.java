package com.example.royalhouse.entity;


import com.example.royalhouse.enums.Building;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "objects")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Object() {
    }

    public Object(Building building, double area, double price, double priceSquareMeter, int rooms, int storey, int countStoreys) {
        this.building = building;
        this.area = area;
        this.price = price;
        this.priceSquareMeter = priceSquareMeter;
        this.rooms = rooms;
        this.storey = storey;
        this.countStoreys = countStoreys;
    }
    public String getImagePath(){
        if(imageFirst==null){
            return null;
        }
        return "/uploads/"+imageFirst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceSquareMeter() {
        return priceSquareMeter;
    }

    public void setPriceSquareMeter(double priceSquareMeter) {
        this.priceSquareMeter = priceSquareMeter;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getStorey() {
        return storey;
    }

    public void setStorey(int storeys) {
        this.storey = storeys;
    }

    public int getCountStoreys() {
        return countStoreys;
    }

    public void setCountStoreys(int under_storeys) {
        this.countStoreys = under_storeys;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public String getImageFirst() {
        return imageFirst;
    }

    public void setImageFirst(String imageFirst) {
        this.imageFirst = imageFirst;
    }

    public String getImageSecond() {
        return imageSecond;
    }

    public void setImageSecond(String imageSecond) {
        this.imageSecond = imageSecond;
    }

    public String getImageThird() {
        return imageThird;
    }

    public void setImageThird(String imageThird) {
        this.imageThird = imageThird;
    }
}
