package com.example.tdddemo;

import com.example.tdddemo.Result;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TddDemoController {

    @Autowired
    NewsService newsService;

    // Get All Expenses
    @GetMapping("/filteredNews")
    public ResponseEntity getNewsBySource(@RequestParam(value = "source", required = true) String source) {

        try {

            CompletableFuture<Result> future = newsService.getNewsStoriesBySource(source);

            return new ResponseEntity<Result>(future.get(), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
