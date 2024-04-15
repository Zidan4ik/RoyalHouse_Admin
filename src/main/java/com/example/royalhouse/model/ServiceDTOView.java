package com.example.royalhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ServiceDTOView {
        private long id;
        private String name;
        private String description;
        private String date;
        private boolean isReflection;
    }
