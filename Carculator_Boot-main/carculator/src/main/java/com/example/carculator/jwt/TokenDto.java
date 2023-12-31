package com.example.carculator.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.carculator.jwt.JwtUtil.BEARER_PREFIX;

@Getter
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private String refreshToken;

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = BEARER_PREFIX + accessToken;
        this.refreshToken = BEARER_PREFIX + refreshToken;
    }

}
