package com.system.blog.services;

import com.system.blog.dtos.CommentDto;


import java.util.List;

public interface CommentService {

    CommentDto createComment(Integer publicationId,CommentDto dto);

    List<CommentDto> getCommentByPublicationId(Integer publicationId);

    CommentDto getCommentById(Integer publicationId,Integer commentId);

    CommentDto updateComment(Integer publicationId,Integer commentId, CommentDto dto);

    void deleteComment(Integer publicationId,Integer commentId);
}
