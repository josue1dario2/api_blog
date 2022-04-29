package com.system.blog.services.impl;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.entities.Publication;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.PublicationService;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDto create(PublicationDto publicationDto) {
        Publication entity = publicationRepository.save(Mapper.mapFromDto(publicationDto));
        return Mapper.mapToDto(entity);
    }

    @Override
    public List<PublicationDto> getAll(int noPage,int sizePage) {
        Pageable pageable = PageRequest.of()
        List<Publication> publications = publicationRepository.findAll();
        return publications.stream().map(publication -> Mapper.mapToDto(publication)).collect(Collectors.toList());
    }

    @Override
    public PublicationDto getById(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("publication","id",id));
        return Mapper.mapToDto(publication);
    }

    @Override
    public PublicationDto update(PublicationDto dto, Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("publication","id",id));

        publication.setTitle(dto.getTitle());
        publication.setDescription(dto.getDescription());
        publication.setContent(dto.getContent());

        Publication publicationUpdate = publicationRepository.save(publication);
        return Mapper.mapToDto(publicationUpdate);
    }

    @Override
    public void delete(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("publication","id",id));
        publicationRepository.delete(publication);
    }
}
