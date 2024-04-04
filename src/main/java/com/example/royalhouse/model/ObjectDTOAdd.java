package com.example.royalhouse.model;

import com.example.royalhouse.enums.Building;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ObjectDTOAdd {
    private Long id;

    @NotNull(message = "виберіть тип будівлі для об'єкту")
    private Building building;

    @DecimalMin(value = "0.0", inclusive = false, message = "Значення не повинно бути нульовим/негативним")
    private double area;

    @DecimalMin(value = "0.0", inclusive = false, message = "Значення не повинно бути нульовим/негативним")
    private double price;

    @DecimalMin(value = "0.0", inclusive = false, message = "Значення не повинно бути нульовим/негативним")
    private double priceSquareMeter;

    @Min(value = 1, message = "Значення не повинно бути нульовим/негативним")
    private int rooms;

    @Min(value = 1, message = "Значення не повинно бути нульовим/негативним")
    private int storey;

    @Min(value = 1, message = "Значення не повинно бути нульовим/негативним")
    private int countStoreys;

    private String imageFirst;
    private String imageSecond;
    private String imageThird;

    public String getLinkToFirstImage() {
        return "/uploads/"+id+"/" + imageFirst;
    }
}
