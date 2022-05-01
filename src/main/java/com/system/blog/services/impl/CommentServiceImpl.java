package com.system.blog.services.impl;

import com.system.blog.dtos.CommentDto;
import com.system.blog.entities.Comment;
import com.system.blog.entities.Publication;
import com.system.blog.exceptions.BlogAppException;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.repositories.CommentRepository;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.CommentService;
import com.system.blog.utils.AppConstants;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getCommentByPublicationId(Long publicationId) {
        List<Comment> comments = commentRepository.findByPublicationId(publicationId);
        return comments.stream().map(Mapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long publicationId,Long commentId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException(AppConstants.PUBLICATION,AppConstants.ID,publicationId));

        Comment comment = commentRepository.findById(commentId)
               .orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"The comment does not belong to the publication");
        }
        return Mapper.mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long publicationId,Long commentId, CommentDto dto) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException(AppConstants.PUBLICATION,AppConstants.ID,publicationId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"The comment does not belong to the publication");
        }
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        Comment updateComment = commentRepository.save(comment);
        return Mapper.mapToDto(updateComment);
    }
}
