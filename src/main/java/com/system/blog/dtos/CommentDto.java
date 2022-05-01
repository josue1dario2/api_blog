package com.system.blog.dtos;

import com.system.blog.entities.Publication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private Long id;
    private String body;
    private String email;
    private String name;

}
