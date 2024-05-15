package com.example.royalhouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String fullName;
    @Column(length = 40)
    private String phone;
    @Column(length = 40)
    private String email;
    @Lob
    @Column(nullable = true,columnDefinition = "text")
    private String comment;
    private LocalDateTime date;
    private boolean isReported;
}
