package com.system.blog.services;

import com.system.blog.dtos.CommentDto;


import java.util.List;

public interface CommentService {

    CommentDto createComment(Long publicationId,CommentDto dto);

    List<CommentDto> getCommentByPublicationId(Long publicationId);

    CommentDto getCommentById(Long publicationId,Long commentId);

    CommentDto updateComment(Long publicationId,Long commentId, CommentDto dto);

    void deleteComment(Long publicationId,Long commentId);
}
