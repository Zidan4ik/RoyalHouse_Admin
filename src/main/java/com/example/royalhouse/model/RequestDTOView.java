package com.example.royalhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTOView {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String comment;
    private String date;
    private boolean isReported;
}
