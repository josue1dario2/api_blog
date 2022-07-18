package com.system.blog.utils;

import com.system.blog.entities.Comment;
import com.system.blog.entities.Publication;
import com.system.blog.exceptions.ResourceNotFoundException;
import com.system.blog.repositories.CommentRepository;
import com.system.blog.repositories.PublicationRepository;

public class AppConstants {

    private AppConstants(){
        throw new IllegalStateException("Utility class");
    }
    public static final String DEFAULT_NUMBER_OF_PAGES = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String SORT_BY_DEFAULT = "id";
    public static final String ORDER_DEFAULT_ADDRESS = "asc";
    public static final String PUBLICATION = "publication";
    public static final String ID = "id";
    public static final String DO_NOT_MATCH = "The comment does not belong to the publication";
    public static final String COMMENT = "comment";

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    //Method const

    public static Publication findyByIdPublication(Integer fieldValue, PublicationRepository r, String resourceName,String fieldName){
        return r.findById(fieldValue)
                .orElseThrow(()-> new ResourceNotFoundException(resourceName,fieldName,fieldValue));
    }
    public static Comment findyByIdComment(Integer fieldValue, CommentRepository r, String resourceName, String fieldName){
        return r.findById(fieldValue)
                .orElseThrow(()-> new ResourceNotFoundException(resourceName,fieldName,fieldValue));
    }

}
