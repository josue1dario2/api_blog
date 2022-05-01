package com.system.blog.dtos;

import com.system.blog.entities.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class PublicationDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;
}
