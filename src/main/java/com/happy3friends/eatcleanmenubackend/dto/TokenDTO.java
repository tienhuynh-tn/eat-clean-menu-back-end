package com.happy3friends.eatcleanmenubackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

}
