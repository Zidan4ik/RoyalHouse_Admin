package com.example.royalhouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String phone;
    @Column(length = 40)
    private String viber;
    @Column(length = 40)
    private String telegram;
    @Column(length = 40)
    private String email;
    @Column(length = 40)
    private String instagram;
    @Column(length = 40)
    private String facebook;
    @Lob
    @Column(length = 40)
    private String address;
    private String password;
    private Double length;
    private Double width;
}
