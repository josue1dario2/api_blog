package com.system.blog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String usernameOrEmail;
    private String password;

}
