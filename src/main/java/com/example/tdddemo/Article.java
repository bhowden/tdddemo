package com.example.tdddemo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Article {

    private Source source;
    private Object author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

}
