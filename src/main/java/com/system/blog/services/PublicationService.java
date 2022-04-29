package com.system.blog.services;

import com.system.blog.dtos.PublicationDto;

import java.util.List;

public interface PublicationService {

    PublicationDto create(PublicationDto publicationDto);

    List<PublicationDto> getAll(int noPage, int sizePage);

    PublicationDto getById(Long id);

    PublicationDto update(PublicationDto dto,Long id);

    void delete(Long id);

}
