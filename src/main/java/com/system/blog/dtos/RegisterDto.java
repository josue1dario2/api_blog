package com.system.blog.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterDto {

    private String name;
    private String username;
    private String email;
    private String password;
}
