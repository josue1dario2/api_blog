package com.system.blog.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtAuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponseDto(String accessToken){
        super();
        this.accessToken = accessToken;
    }
}
