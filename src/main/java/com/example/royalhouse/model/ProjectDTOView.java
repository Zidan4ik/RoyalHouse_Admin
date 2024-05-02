package com.example.royalhouse.model;

import lombok.Data;

@Data
public class ProjectDTOView {
    private int id;
    private int indexNum;
    private String name;
    private String address;
    private boolean isActive;
}
