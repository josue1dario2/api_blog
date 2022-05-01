package com.system.blog.services.impl;

import com.system.blog.dtos.CommentDto;
import com.system.blog.repositories.CommentRepository;
import com.system.blog.repositories.PublicationRepository;
import com.system.blog.services.CommentService;
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
        return null;
    }
}
