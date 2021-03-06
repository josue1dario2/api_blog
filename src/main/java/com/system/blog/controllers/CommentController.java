package com.system.blog.controllers;

import com.system.blog.dtos.CommentDto;
import com.system.blog.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/")
public class CommentController {

    private final CommentServiceImpl commentService;
    @Autowired
    public CommentController (CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping("/publications/{publicationId}/comments")
    public List<CommentDto> listCommentsByPublicationId(@PathVariable Integer publicationId){
        return commentService.getCommentByPublicationId(publicationId);
    }

    @GetMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer publicationId,@PathVariable Integer id){
        return new ResponseEntity<>(commentService.getCommentById(publicationId,id),HttpStatus.OK);
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable Integer publicationId,@Valid  @RequestBody CommentDto dto){
        return new ResponseEntity<>(commentService.createComment(publicationId,dto), HttpStatus.CREATED);
    }
    @PutMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer publicationId,@PathVariable Integer id,@Valid @RequestBody CommentDto dto){
        return new ResponseEntity<>(commentService.updateComment(publicationId,id,dto),HttpStatus.OK);
    }
    @DeleteMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer publicationId,@PathVariable Integer id){
        commentService.deleteComment(publicationId,id);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
