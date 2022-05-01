package com.system.blog.services;

import com.system.blog.dtos.CommentDto;

public interface CommentService {

    CommentDto create(Long publicationId,CommentDto dto);
}
