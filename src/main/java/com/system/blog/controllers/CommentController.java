package com.system.blog.controllers;

import com.system.blog.dtos.CommentDto;
import com.system.blog.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable Long publicationId,@RequestBody CommentDto dto){
        return new ResponseEntity<>(commentService.create(publicationId,dto), HttpStatus.CREATED);
    }
}
