package com.system.blog.services.impl;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.dtos.PublicationResponse;
import com.system.blog.entities.Publication;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.PublicationService;
import com.system.blog.utils.AppConstants;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    
    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository){
        this.publicationRepository = publicationRepository;
    }

    @Override
    public PublicationDto create(PublicationDto publicationDto) {
        Publication entity = publicationRepository.save(Mapper.mapFromDto(publicationDto));
        return Mapper.mapToDto(entity);
    }

    @Override
    public PublicationResponse getAll(int noPage, int sizePage,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(noPage,sizePage, sort);

        Page<Publication> publicationsPage = publicationRepository.findAll(pageable);
        List<Publication> publications = publicationsPage.getContent();
        List<PublicationDto> content =  publications
                .stream().map(Mapper::mapToDto)
                .collect(Collectors.toList());

        return Mapper.mapToDto(content,publicationsPage);
    }

    @Override
    public PublicationDto getById(Integer id) {
        return Mapper.mapToDto(AppConstants
                .findyByIdPublication(id,publicationRepository,AppConstants.PUBLICATION,AppConstants.ID));
    }

    @Override
    public PublicationDto update(PublicationDto dto, Integer id) {
        Publication publication = AppConstants
                .findyByIdPublication(id,publicationRepository,AppConstants.PUBLICATION,AppConstants.ID);

        publication.setTitle(dto.getTitle());
        publication.setDescription(dto.getDescription());
        publication.setContent(dto.getContent());

        Publication publicationUpdate = publicationRepository.save(publication);
        return Mapper.mapToDto(publicationUpdate);
    }

    @Override
    public void delete(Integer id) {
        publicationRepository.delete(AppConstants
                .findyByIdPublication(id,publicationRepository,AppConstants.PUBLICATION,AppConstants.ID));
    }
}
