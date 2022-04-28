package com.system.blog.services.impl;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.entities.Publication;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.PublicationService;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDto create(PublicationDto publicationDto) {
        Publication entity = publicationRepository.save(Mapper.mapFromDto(publicationDto));
        return Mapper.mapToDto(entity);
    }
}
