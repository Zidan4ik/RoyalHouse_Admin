package com.example.royalhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDTO {
    private Long id;
    private String linkImage1;
    private String linkImage2;
    private String linkImage3;
}
