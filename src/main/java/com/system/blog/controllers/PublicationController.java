package com.system.blog.controllers;

import com.system.blog.dtos.PublicationDto;
import com.system.blog.dtos.PublicationResponse;
import com.system.blog.services.impl.PublicationServiceImpl;
import com.system.blog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/publications")
public class PublicationController {

    private final PublicationServiceImpl publicationService;
    @Autowired
    public PublicationController(PublicationServiceImpl publicationService){
        this.publicationService = publicationService;
    }

    @GetMapping
    public PublicationResponse getAllPublications(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_NUMBER_OF_PAGES,required = false) int numPage,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int sizePage,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY_DEFAULT,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.ORDER_DEFAULT_ADDRESS,required = false) String sortDir
            ){
        return publicationService.getAll(numPage,sizePage,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> getPublicationById(@PathVariable Long id){
        return ResponseEntity.ok(publicationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PublicationDto> savePublication(@Valid @RequestBody PublicationDto dto){
        return new ResponseEntity<>(publicationService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDto> updatePublication(@PathVariable Long id,@Valid @RequestBody PublicationDto dto){
        return ResponseEntity.ok(publicationService.update(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Long id){
        publicationService.delete(id);
        return ResponseEntity.ok("Publication deleted successfully");
    }
}
