package com.example.royalhouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String phone;
    private String email;
    private String comment;
    private LocalDateTime date;
    private boolean isReported;
}
