package com.happy3friends.eatcleanmenubackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDietaryTrackingRequest {
    private double userCalories;
    private int menuDishId;
}
