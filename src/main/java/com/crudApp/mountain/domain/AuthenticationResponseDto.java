package com.crudApp.mountain.domain;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthenticationResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
