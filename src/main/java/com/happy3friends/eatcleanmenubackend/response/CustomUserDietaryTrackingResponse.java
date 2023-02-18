package com.happy3friends.eatcleanmenubackend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDietaryTrackingResponse {
    String mealTime;
    String status;
    double calories;
    String mealDate;
}
