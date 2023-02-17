package com.happy3friends.eatcleanmenubackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDietaryInfoDTO {
    private int id;
    private String dietTarget;
    private int userAge;
    private double userHeight;
    private double userWeight;
    private String activityRate;
    private double bmi;
    private double bmr;
    private int userId;
    private Double caloriesConsumed;
    private String gender;
}
