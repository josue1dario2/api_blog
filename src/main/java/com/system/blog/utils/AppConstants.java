package com.system.blog.utils;

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

}
