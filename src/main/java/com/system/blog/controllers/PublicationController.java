package com.system.blog.controllers;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.services.impl.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/publications")
public class PublicationController {

    @Autowired
    private PublicationServiceImpl publicationService;

    @GetMapping
    public List<PublicationDto> getAllPublications(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int numPage,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int sizePage
            ){
        return publicationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> getPublicationById(@PathVariable Long id){
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PublicationDto> savePublication(@RequestBody PublicationDto dto){
        return new ResponseEntity<>(publicationService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDto> updatePublication(@PathVariable Long id,@RequestBody PublicationDto dto){
        return ResponseEntity.ok(publicationService.update(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Long id){
        publicationService.delete(id);
        return ResponseEntity.ok("Publication deleted successfully");
    }
}
