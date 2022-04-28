package com.system.blog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
