package com.example.royalhouse.model;

import com.example.royalhouse.entity.EmailRequest;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsDTOEdit {
    private Long id;
    private String phone;
    private String viber;
    private String telegram;
    private String email;
    private String instagram;
    private String facebook;
    private String address;
    private String password;

    private String repeatPassword;
    private String newPassword;
    private List<EmailRequest> emails;
}
