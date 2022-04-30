package com.system.blog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PublicationResponse {

    private List<PublicationDto> content;
    private int numPage;
    private int sizePage;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
