package com.system.blog.dtos;

import com.system.blog.entities.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
public class PublicationDto {

    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "The title must have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "The description must have at least 10 characters")
    private String description;

    @NotEmpty(message = "Must not be empty")
    private String content;

    private Set<Comment> comments;
}
