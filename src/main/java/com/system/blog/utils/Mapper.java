package com.system.blog.utils;

import com.system.blog.dtos.CommentDto;
import com.system.blog.dtos.PublicationDto;
import com.system.blog.entities.Comment;
import com.system.blog.entities.Publication;

public class Mapper {

    private Mapper(){}

    public static PublicationDto mapToDto(Publication entity){
        return PublicationDto.builder()
               .id(entity.getId())
               .title(entity.getTitle())
               .description(entity.getDescription())
               .content(entity.getContent())
                .comments(entity.getComments())
               .build();
    }
    public static Publication mapFromDto(PublicationDto dto){
        return Publication.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .build();
    }
    public static CommentDto mapToDto(Comment entity){
        return CommentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .body(entity.getBody())
                .build();
    }
    public static Comment mapFromDto(CommentDto dto){
        return Comment.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .body(dto.getBody())
                .build();
    }

}
