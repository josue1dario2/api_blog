package com.system.blog.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CommentDto {

    private Long id;
    @NotEmpty
    @Size(min = 10, message = "The comment body must have at least 10 characters")
    private String body;
    @NotEmpty(message = "The email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "The name must not be empty")
    private String name;

}
