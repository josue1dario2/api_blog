package com.system.blog.services;

import com.system.blog.dtos.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(Long publicationId,CommentDto dto);

    List<CommentDto> getCommentByPublicationId(Long publicationId);

    CommentDto getCommentById(Long publicationId,Long commentId);
}
