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

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/publications/{publicationId}/comments")
    public List<CommentDto> listCommentsByPublicationId(@PathVariable Long publicationId){
        return commentService.getCommentByPublicationId(publicationId);
    }

    @GetMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long publicationId,@PathVariable Long id){
        return new ResponseEntity<>(commentService.getCommentById(publicationId,id),HttpStatus.OK);
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable Long publicationId,@Valid  @RequestBody CommentDto dto){
        return new ResponseEntity<>(commentService.createComment(publicationId,dto), HttpStatus.CREATED);
    }
    @PutMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long publicationId,@PathVariable Long id,@Valid @RequestBody CommentDto dto){
        return new ResponseEntity<>(commentService.updateComment(publicationId,id,dto),HttpStatus.OK);
    }
    @DeleteMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long publicationId,@PathVariable Long id){
        commentService.deleteComment(publicationId,id);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
