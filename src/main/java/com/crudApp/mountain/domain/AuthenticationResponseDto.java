package com.crudApp.mountain.domain;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String accessToken;

    public AuthenticationResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
