package com.crudApp.mountain.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthenticationResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
