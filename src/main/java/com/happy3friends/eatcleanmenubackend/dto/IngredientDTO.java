package com.happy3friends.eatcleanmenubackend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private String name;
    private String quantity;
    private String type;
}
