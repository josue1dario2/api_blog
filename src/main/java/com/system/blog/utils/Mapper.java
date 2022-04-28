package com.system.blog.utils;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.entities.Publication;

public class Mapper {

    public static PublicationDto mapToDto(Publication entity){
        return PublicationDto.builder()
               .id(entity.getId())
               .title(entity.getTitle())
               .description(entity.getDescription())
               .content(entity.getContent())
               .build();
    }
    public static Publication mapFromDto(PublicationDto dto){
        return Publication.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .build();
    }

}
