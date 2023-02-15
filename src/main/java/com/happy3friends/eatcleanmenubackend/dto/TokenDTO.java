package com.happy3friends.eatcleanmenubackend.dto;

import lombok.*;

@Data
public class TokenDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

}
