package com.happy3friends.eatcleanmenubackend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDietaryInfoDTO {
    private int id;
    @ApiModelProperty(value = "dietTarget", example = "Giảm cân")
    private String dietTarget;
    @ApiModelProperty(value = "userAge", example = "10")
    private int userAge;
    @ApiModelProperty(value = "userHeight", example = "160")
    private double userHeight;
    @ApiModelProperty(value = "userWeight", example = "50")
    private double userWeight;
    @ApiModelProperty(value = "activityRate", example = "Ít tập luyện")
    private String activityRate;
    @ApiModelProperty(value = "bmi", example = "120")
    private double bmi;
    @ApiModelProperty(value = "bmr", example = "150")
    private double bmr;
    private int userId;
}
