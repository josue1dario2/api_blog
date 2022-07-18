package com.system.blog.utils;

import com.system.blog.dtos.CommentDto;
import com.system.blog.dtos.PublicationDto;
import com.system.blog.dtos.PublicationResponse;
import com.system.blog.dtos.RegisterDto;
import com.system.blog.entities.Comment;
import com.system.blog.entities.Publication;
import com.system.blog.entities.Role;
import com.system.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

public class Mapper {

    private Mapper(){}

    public static PublicationDto mapToDto(Publication entity){
        return PublicationDto.builder()
               .id(entity.getIdPublication())
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
                .id(entity.getIdComment())
                .name(entity.getName())
                .email(entity.getEmail())
                .body(entity.getBody())
                .build();
    }
    public static Comment mapFromDto(CommentDto dto){
        return Comment.builder()
                .idComment(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .body(dto.getBody())
                .build();
    }
    public static PublicationResponse mapToDto(List<PublicationDto> content, Page<Publication> publicationsPage){
        return PublicationResponse.builder()
                .content(content)
                .numPage(publicationsPage.getNumber())
                .sizePage(publicationsPage.getSize())
                .totalElements(publicationsPage.getTotalElements())
                .totalPages(publicationsPage.getTotalPages())
                .last(publicationsPage.isLast())
                .build();
    }
    public static User mapFromDto(RegisterDto dto, PasswordEncoder passwordEncoder, Role roles){
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singleton(roles))
                .build();
    }

}
