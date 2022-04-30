package com.system.blog.services;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.dtos.PublicationResponse;


public interface PublicationService {

    PublicationDto create(PublicationDto publicationDto);

    PublicationResponse getAll(int noPage, int sizePage,String sortBy,String sortDir);

    PublicationDto getById(Long id);

    PublicationDto update(PublicationDto dto,Long id);

    void delete(Long id);

}
