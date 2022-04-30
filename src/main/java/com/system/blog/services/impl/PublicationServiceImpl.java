package com.system.blog.services.impl;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.dtos.PublicationResponse;
import com.system.blog.entities.Publication;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.PublicationService;
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

    private static final String PUBLICATION = "publication";
    private static final String ID = "id";

    @Autowired
    private PublicationRepository publicationRepository;

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
                .stream().map(publication -> Mapper.mapToDto(publication))
                .collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContent(content);
        publicationResponse.setNumPage(publicationsPage.getNumber());
        publicationResponse.setSizePage(publicationsPage.getSize());
        publicationResponse.setTotalElements(publicationsPage.getTotalElements());
        publicationResponse.setTotalPages(publicationsPage.getTotalPages());
        publicationResponse.setLast(publicationsPage.isLast());
        return publicationResponse;
    }

    @Override
    public PublicationDto getById(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(PUBLICATION,ID,id));
        return Mapper.mapToDto(publication);
    }

    @Override
    public PublicationDto update(PublicationDto dto, Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(PUBLICATION,ID,id));

        publication.setTitle(dto.getTitle());
        publication.setDescription(dto.getDescription());
        publication.setContent(dto.getContent());

        Publication publicationUpdate = publicationRepository.save(publication);
        return Mapper.mapToDto(publicationUpdate);
    }

    @Override
    public void delete(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(PUBLICATION,ID,id));
        publicationRepository.delete(publication);
    }
}
