package com.system.blog.services.impl;

import com.system.blog.dtos.CommentDto;
import com.system.blog.entities.Comment;
import com.system.blog.entities.Publication;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.repositories.CommentRepository;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.CommentService;
import com.system.blog.utils.AppConstants;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public CommentDto create(Long publicationId, CommentDto dto) {
        Comment comment = Mapper.mapFromDto(dto);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException(AppConstants.PUBLICATION, AppConstants.ID,publicationId));
        comment.setPublication(publication);
        Comment newComment = commentRepository.save(comment);
        return Mapper.mapToDto(newComment);
    }
}
