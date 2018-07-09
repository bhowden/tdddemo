package com.example.tdddemo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Result {

    private String status;
    private Integer totalResults;
    private List<Article> articles = null;

}
